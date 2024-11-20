package com.cromxt.user.config;

import com.cromxt.user.repository.TokenRepository;
import com.cromxt.user.service.JWTService;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

import java.io.IOException;


@RequiredArgsConstructor
public class RefreshTokenAuthorizationFilter implements Filter {
    private final JWTService jwtService;
    private final UserDetailsService userDetailsService;
    private final TokenRepository tokenRepository;
    private final static String TOKEN_URL = "/tokens";

    @Override
    public void doFilter(
            @NonNull ServletRequest req,
            @NonNull ServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {

        final HttpServletRequest request = (HttpServletRequest) req;

        String requestURI = request.getRequestURI().substring(7);

        if(!requestURI.startsWith(TOKEN_URL)) {
            filterChain.doFilter(request, response);
            return;
        }

        final String authHeader = request.getHeader("refreshToken");

        final String jwt;
        final String username;
        if (authHeader == null) {
            filterChain.doFilter(request, response);
            return;
        }
        jwt = authHeader;
        username = jwtService.extractUsername(jwt);
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
            var isTokenValid = tokenRepository.findByToken(jwt)
                    .map(t -> !t.getRevoked() && !t.getExpired())
                    .orElse(false);
            if (jwtService.isRefreshTokenValid(jwt, userDetails) && isTokenValid) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );
                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(request, response);
    }
}
