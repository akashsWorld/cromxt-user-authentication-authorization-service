package com.cromxt.user.config;


import com.cromxt.user.exceptions.UserNotFoundException;
import com.cromxt.user.repository.UserEntityRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class UserServiceConfiguration {
    private UserEntityRepository userEntityRepository;

    @Bean
    public DateFormatter dateFormatter(){
        //    TODO: use formatter to format the date.
        DateFormatter dateFormatter =  new DateFormatter("dd/MM/yyyy");
        dateFormatter.setIso(DateTimeFormat.ISO.DATE);
        return dateFormatter;
    }
    @Bean
    public UserDetailsService userDetailsService(){
        return username -> userEntityRepository.findByUsername(username).orElseThrow(
                ()-> new UserNotFoundException("User with username: " + username + " not found")
        );
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public AuthenticationProvider authenticationProvider(UserDetailsService userDetailsService,PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder);
        return authProvider;
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
