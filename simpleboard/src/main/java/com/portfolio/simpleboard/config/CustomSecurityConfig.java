package com.portfolio.simpleboard.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.HttpSecurityBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatchers;

import java.util.Arrays;
import java.util.List;

@Configuration
public class CustomSecurityConfig {



    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        String[] publicPath= {
                "/"
        };
        String[] signPath = {
                "/"
        };
        http.authorizeHttpRequests(custom->{
            custom
                    .requestMatchers(publicPath).permitAll()
                    .requestMatchers(signPath).permitAll()
                    .anyRequest().permitAll();
        });
        return http.build();
    }
}
