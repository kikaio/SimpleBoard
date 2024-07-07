package com.portfolio.simpleboard.config;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModuleConfig {

    @Bean
    public ObjectMapper objectMapper(){
        var om = new ObjectMapper();
        om.registerModule(new JavaTimeModule());
        return om;
    }
}
