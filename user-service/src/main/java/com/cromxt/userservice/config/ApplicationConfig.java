package com.cromxt.userservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.cromxt.jwt.JWTService;
import com.cromxt.jwt.JWTServiceImpl;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class ApplicationConfig {


    @Bean
    PasswordEncoder passwordEncoder (){
        return new BCryptPasswordEncoder();
    }

    @Bean
    JWTService jwtService(Environment environment){
        String secret = environment.getProperty("USER_CONFIG_JWT_SECRET", String.class);
        long accessTokenExpiration = environment.getProperty("USER_CONFIG_JWT_ACCESS_TOKEN_EXPIRATION", Long.class);
        long refreshTokenExpiration = environment.getProperty("USER_CONFIG_JWT_REFRESH_TOKEN_EXPIRATION", Long.class);
        
        log.info("secret: {}, accessTokenExpiration: {}, refreshTokenExpiration: {}", secret, accessTokenExpiration, refreshTokenExpiration);
        
        // assert secret != null;
        // assert accessTokenExpiration != null;
        // assert refreshTokenExpiration != null;
        return new JWTServiceImpl(secret, accessTokenExpiration, refreshTokenExpiration);
    }

}
