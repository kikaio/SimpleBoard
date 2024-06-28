package com.portfolio.simpleboard.entity;

import com.portfolio.simpleboard.entity.base.DateEntity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@Builder
@Getter()
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
public class MemberProfile extends DateEntity implements Serializable, UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String nickname;

    @Builder.Default
    private Boolean isDel = false;

    @Builder.Default
    private List<SimpleGrantedAuthority> simpleGrantedAuthorities = new ArrayList<>();

    @Override
    public Collection<SimpleGrantedAuthority> getAuthorities(){
        return simpleGrantedAuthorities;
    }

    public String password;

    @Override
    public String getPassword(){
        return "";
    }

    @Override
    public String getUsername(){
        return nickname;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    //todo : 회원 관련 제한 처리 등은 추후 할 예정
    @Override
    public boolean isAccountNonExpired(){
        return true;
    }

    @Override
    public boolean isAccountNonLocked(){
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired(){
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
