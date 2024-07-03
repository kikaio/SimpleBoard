package com.portfolio.simpleboard.dto.member;


import com.portfolio.simpleboard.entity.MemberProfile;
import lombok.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberProfileDetailDTO {

    private Long id;

    private String nickname;

    @Builder.Default
    private boolean isDel = false;

    @Builder.Default
    private List<SimpleGrantedAuthority> authorities = new ArrayList<>();

    @Builder.Default
    private boolean isAccountNonExpired = true;

    @Builder.Default
    private boolean isAccountNonLocked = true;

    @Builder.Default
    private boolean isCredentialsNonExpired = true;

    @Builder.Default
    private boolean isEnabled = true;

    public static MemberProfile toEntity(MemberProfileDetailDTO memberProfileDetailDTO) {
        if (memberProfileDetailDTO == null) {
            return null;
        }
        return MemberProfile.builder()
                .id(memberProfileDetailDTO.getId())
                .nickname(memberProfileDetailDTO.getNickname())
                .isDel(memberProfileDetailDTO.isDel)
                .build();
    }

    public static MemberProfileDetailDTO fromEntity(MemberProfile profile) {
        if(profile == null) {
            return null;
        }
        var dto = MemberProfileDetailDTO.builder()
                .id(profile.getId())
                .nickname(profile.getNickname())
                .isDel(profile.getIsDel())
                .isAccountNonExpired(profile.isAccountNonExpired())
                .isAccountNonLocked(profile.isAccountNonLocked())
                .isCredentialsNonExpired(profile.isCredentialsNonExpired())
                .isEnabled(profile.isEnabled())
                .build();
        dto.authorities.clear();
        dto.authorities.addAll(profile.getAuthorities());
        return dto;
    }
}
