package com.portfolio.simpleboard.session;


import com.portfolio.simpleboard.entity.MemberProfile;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Component
@ToString
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
@Builder
public class MemberSession implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String nickname;

    @Builder.Default
    private Boolean isDel = false;

    @Builder.Default
    private Boolean isAccountNonExpired = true;

    @Builder.Default
    private Boolean isAccountNonLocked = true;

    @Builder.Default
    private Boolean isCredentialsNonExpired = true;

    @Builder.Default
    private Boolean isEnabled = true;

    private String email;

    private String password;

    @Builder.Default
    private List<String> authorities = new ArrayList<>();

    public static MemberSession fromEntity(MemberProfile profile) {
        var memberSession = MemberSession.builder()
                .id(profile.getId())
                .nickname(profile.getNickname())
                .isDel(profile.getIsDel())
                .isAccountNonExpired(profile.isAccountNonExpired())
                .isAccountNonLocked(profile.getIsAccountNonLocked())
                .isCredentialsNonExpired(profile.getIsCredentialsNonExpired())
                .isEnabled(profile.isEnabled())
                .email(profile.getEmail())
                .password(profile.getPassword())
                .build();

        //보유 권한 정보 문자열로 치환해서 저장.
        for(var ele : profile.getAuthorities()) {
            memberSession.authorities.add(ele.getAuthority());
        }
        return memberSession;
    }
}
