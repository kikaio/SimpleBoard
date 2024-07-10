package com.portfolio.simpleboard.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.portfolio.simpleboard.entity.base.DateEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonDeserialize
public class MemberProfile extends DateEntity implements Serializable, UserDetails {

    @Transient
    private static final long serialVersionUID = 362498820763181265L;

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
    private List<String> simpleGrantedAuthorities = new ArrayList<>();

    @Transient
    private String email;

    @Transient
    private String password;


    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return simpleGrantedAuthorities.stream().map(ele->{
            return new SimpleGrantedAuthority(ele);
        }).collect(Collectors.toList());
    }

    public void addAuthority(SimpleGrantedAuthority simpleGrantedAuthority) {
        simpleGrantedAuthorities.add(simpleGrantedAuthority.getAuthority());
    }

    @Override
    public String getPassword(){
        return password;
    }

    @JsonIgnore
    @Override
    public String getUsername(){
        return this.nickname;
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
    @JsonIgnore
    @Override
    public boolean isAccountNonExpired(){
        return isAccountNonExpired;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked(){
        return isAccountNonLocked;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired(){
        return isCredentialsNonExpired;
    }

    @JsonIgnore
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

