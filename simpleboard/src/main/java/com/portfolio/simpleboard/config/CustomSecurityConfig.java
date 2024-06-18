package com.portfolio.simpleboard.config;


import com.portfolio.simpleboard.security.Custom403Handler;
import com.portfolio.simpleboard.service.SimpleBoardUserDetailService;
import jakarta.servlet.DispatcherType;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.HttpSecurityBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatchers;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.List;

@Log4j2
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
@Configuration
public class CustomSecurityConfig {

    private final DataSource dataSource;

    private final SimpleBoardUserDetailService simpleBoardUserDetailService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        String[] publicPath= {
                "/"
                , "/favicon.ico"
        };
        String[] signPath = {
                "/member/login"
        };
        http.authorizeHttpRequests(custom->{
            custom
                    .dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
                    .requestMatchers(publicPath).permitAll()
                    .requestMatchers(signPath).permitAll()
                    .anyRequest().authenticated();
        });
        http.userDetailsService(simpleBoardUserDetailService);
        http.csrf(custom->{
            custom.disable();
        });

        http.formLogin(custom->{
            custom.loginPage("/member/login")
            ;
        });
        http.rememberMe(custom->{
            custom
                    .key("4312312421")
                    .tokenRepository(persistentTokenRepository())
                    .userDetailsService(simpleBoardUserDetailService)
                    .tokenValiditySeconds(60 * 60 * 24 * 30)
                    .rememberMeParameter("remember-me")
            ;
        });
        return http.build();
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl repo = new JdbcTokenRepositoryImpl();
        repo.setDataSource(dataSource);
        return repo;
    }


    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        log.info("---------------web security customizer ---------------");
        return (web)->{
            web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
        };
    }
}
