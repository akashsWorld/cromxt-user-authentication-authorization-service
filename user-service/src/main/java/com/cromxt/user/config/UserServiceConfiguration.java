package com.cromxt.user.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class UserServiceConfiguration {

    @Bean
    public DateFormatter dateFormatter(){
        //    TODO: use formatter to format the date.
        DateFormatter dateFormatter =  new DateFormatter("dd/MM/yyyy");
        dateFormatter.setIso(DateTimeFormat.ISO.DATE);
        return dateFormatter;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


}
