package com.portfolio.simpleboard.entity;

import com.portfolio.simpleboard.entity.base.DateEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
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
@Getter
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

    @Column
    @Builder.Default
    private Boolean isDel = false;

    @ColumnDefault("true")
    @Builder.Default
    private Boolean isAccountNonExpired = true;

    @ColumnDefault("true")
    @Builder.Default
    private Boolean isAccountNonLocked = true;

    @ColumnDefault("true")
    @Builder.Default
    private Boolean isCredentialsNonExpired = true;

    @ColumnDefault("true")
    @Builder.Default
    private Boolean isEnabled = true;



    @Builder.Default
    @Transient
    private List<SimpleGrantedAuthority> simpleGrantedAuthorities = new ArrayList<>();

    @Transient
    private String email;

    @Transient
    public String password;



    @Override
    public Collection<SimpleGrantedAuthority> getAuthorities() {
        return simpleGrantedAuthorities;
    }

    public void addAuthority(SimpleGrantedAuthority simpleGrantedAuthority) {
        simpleGrantedAuthorities.add(simpleGrantedAuthority);
    }

    @Override
    public String getPassword(){
        return password;
    }

    @Override
    public String getUsername(){
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return this.email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    //todo : 회원 관련 제한 처리 등은 추후 할 예정
    @Override
    public boolean isAccountNonExpired(){
        return isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked(){
        return isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired(){
        return isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }


    public void modifyDetail(MemberProfile profile) {
        if(profile.getNickname() !=null && profile.getNickname() != "") {
            this.nickname = profile.getNickname();
        }
        // 각종 flag 값은 별도 API를 통해 변경하는것으로 한다.
    }

    public void modifyIsDel(boolean isDel) {
        this.isDel = isDel;
    }

    public void modifyIsCredentialsNonExpired(boolean isCredentialsNonExpired) {
        this.isCredentialsNonExpired = isCredentialsNonExpired;
    }

    public void modifyIsAccountNonExpired(boolean isAccountNonExpired) {
        this.isAccountNonExpired = isAccountNonExpired;
    }

    public void modifyIsAccountNonLocked(boolean isAccountNonLocked) {
        this.isAccountNonLocked = isAccountNonLocked;
    }

    public void modifyIsEnabled(boolean isEnabled) {
        this.isEnabled = isEnabled;
    }
}

