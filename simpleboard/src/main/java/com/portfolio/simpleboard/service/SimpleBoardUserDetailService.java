package com.portfolio.simpleboard.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class SimpleBoardUserDetailService implements UserDetailsService {

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("login try user name : %s".formatted(username));
        //todo : 유저 별 권한 정보 및 역할 로딩 후 반영

        UserDetails userDetails = User.builder()
                .username(username)
                .authorities("ROLE_USER")
                .password(passwordEncoder.encode("1111"))
                .build();
        return userDetails;
    }

}
