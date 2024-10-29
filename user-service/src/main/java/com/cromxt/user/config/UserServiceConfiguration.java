package com.cromxt.user.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.datetime.DateFormatter;

@Configuration
public class UserServiceConfiguration {

    @Bean
    public DateFormatter dateFormatter(){
        //    TODO: use formatter to format the date.
        DateFormatter dateFormatter =  new DateFormatter("dd/MM/yyyy");
        dateFormatter.setIso(DateTimeFormat.ISO.DATE);
        return dateFormatter;
    }
}
