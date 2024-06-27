package com.portfolio.simpleboard.dto.member;


import com.portfolio.simpleboard.entity.MemberOwnRole;
import com.portfolio.simpleboard.entity.MemberProfile;
import com.portfolio.simpleboard.entity.MemberRole;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@ToString
public class MemberOwnRoleDTO {

    private MemberProfileDTO memberProfileDTO;

    private MemberRoleDTO memberRoleDTO;

    public static MemberOwnRole toEntity(MemberProfileDTO profileDTO, MemberRoleDTO memberRoleDTO) {
        var id = new MemberOwnRole.MemberOwnRoleId(MemberProfileDTO.toEntity(profileDTO), MemberRoleDTO.toEntity(memberRoleDTO));
        return MemberOwnRole.builder()
                .memberOwnRoleId(id)
                .build();
    }

    public static MemberOwnRoleDTO fromEntity(MemberOwnRole entity) {
        return MemberOwnRoleDTO.builder()
                .memberProfileDTO(MemberProfileDTO.fromEntity(entity.getMemberOwnRoleId().getMemberProfile()))
                .memberRoleDTO(MemberRoleDTO.fromEntity(entity.getMemberOwnRoleId().getMemberRole()))
                .build();
    }

}
