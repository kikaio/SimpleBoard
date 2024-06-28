package com.portfolio.simpleboard.dto.member;


import com.portfolio.simpleboard.entity.AccountPlatform;
import com.portfolio.simpleboard.entity.MemberProfile;
import com.portfolio.simpleboard.enums.OAuthPlatform;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class MemberSignUpDTO {

    private String nickname;
    private String email;
    private String password;
    private OAuthPlatform oAuthPlatform;

    public MemberProfile toMemberProfile() {
        return MemberProfile.builder()
                .nickname(nickname)
                .password(password)
                .build();
    }

    public AccountPlatform toAccountPlatform() {

        return AccountPlatform.builder()
                .email(email)
                .platformType(oAuthPlatform)
                .password(password)
                .build();
    }
}
