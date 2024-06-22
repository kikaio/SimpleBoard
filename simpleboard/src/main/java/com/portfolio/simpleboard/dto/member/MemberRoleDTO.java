package com.portfolio.simpleboard.dto.member;


import com.portfolio.simpleboard.entity.MemberRole;
import lombok.*;


@Builder
@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
public class MemberRoleDTO {

    private Long id;
    private String name;
    private String description;

    public static MemberRole toEntity(MemberRoleDTO dto) {
        return MemberRole.builder()
                .id(dto.getId())
                .name(dto.getName())
                .description(dto.getDescription())
                .build();
    }

    public static MemberRoleDTO fromEntity(MemberRole memberRole) {
        return MemberRoleDTO.builder()
                .id(memberRole.getId())
                .name(memberRole.getName())
                .description(memberRole.getDescription())
                .build();
    }
}
