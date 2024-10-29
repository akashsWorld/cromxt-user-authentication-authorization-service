package com.cromxt.user.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.datetime.DateFormatter;

@Configuration
public class UserServiceConfiguration {


//    TODO: use formatter to format the date.
    @Bean
    public DateFormatter dateFormatter(){
        DateFormatter dateFormatter =  new DateFormatter("dd/MM/yyyy");
        dateFormatter.setIso(DateTimeFormat.ISO.DATE);
        return dateFormatter;
    }
}
