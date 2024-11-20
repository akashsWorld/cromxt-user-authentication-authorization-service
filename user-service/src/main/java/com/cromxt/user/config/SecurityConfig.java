package com.cromxt.user.config;


import com.cromxt.user.repository.TokenRepository;
import com.cromxt.user.service.JWTService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private static final String[] WHITE_LIST_URL = {
            "/auth/**",
            "/form/**",
            "/validate",
            "/users/**",
//            "/**" /* Remove this on the production. */
    };

    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http,
            JWTService jwtService,
            UserDetailsService userDetailsService,
            TokenRepository tokenRepository
    ) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                        requestMatchersRegistry->
                                requestMatchersRegistry
                                .requestMatchers(WHITE_LIST_URL).permitAll()
                                .anyRequest().authenticated()
                )
                .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(new RefreshTokenAuthorizationFilter(jwtService,userDetailsService,tokenRepository), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(new AccessTokenAuthorizationFilter(jwtService), RefreshTokenAuthorizationFilter.class)
                .authenticationProvider(authenticationProvider);
        return http.build();
    }
}
