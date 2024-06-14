package com.portfolio.simpleboard.security;

import com.portfolio.simpleboard.entity.AccountPlatform;
import com.portfolio.simpleboard.entity.MemberOwnGrant;
import com.portfolio.simpleboard.entity.MemberOwnRole;
import lombok.Builder;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Log4j2
@Builder
public class SimpleUserDetails implements UserDetails {

    //password for account
    private String password;

    //id for account
    private String userName;

    //flag about account expired
    @Builder.Default
    private boolean isAccountExpired = false;

    //flag about account locked
    @Builder.Default
    private boolean isLocked =  false;

    //flag about credential was expired
    @Builder.Default
    private boolean isCredentialExpired = false;

    //flag about account enabled
    @Builder.Default
    private boolean isEnabled = true;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> grantList = new ArrayList<>();
        return grantList;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.isAccountExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.isLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.isCredentialExpired;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }


    public void setFromAccountPlatform(AccountPlatform accountPlatform) {
        return ;
    }

    public void setFromOwnRole(List<MemberOwnRole> ownRoles) {
        return ;
    }

    public void setFromOwnGrant(List<MemberOwnGrant> ownGrants) {
        return ;
    }
}
