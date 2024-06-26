package com.portfolio.simpleboard.dto.member;

import com.portfolio.simpleboard.entity.MemberProfile;
import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberProfileDTO {

    private Long id;

    private String nickname;

    public static MemberProfile toEntity(MemberProfileDTO memberProfileDTO) {
        if(memberProfileDTO == null){
            return null;
        }

        return MemberProfile.builder()
                .id(memberProfileDTO.getId())
                .nickname(memberProfileDTO.getNickname())
                .build();
    }

    public static MemberProfileDTO fromEntity(MemberProfile memberProfile) {
        if(memberProfile == null) {
            return null;
        }
        return MemberProfileDTO.builder()
                .id(memberProfile.getId())
                .nickname(memberProfile.getNickname())
                .build();
    }
}
