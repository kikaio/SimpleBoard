package com.portfolio.simpleboard.dto.member;


import com.portfolio.simpleboard.entity.MemberGrant;
import lombok.*;

@Data
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class MemberGrantDTO {

    private Long id;

    private String name;

    private String description;

    public static MemberGrant toEntity(MemberGrantDTO memberGrantDTO) {
        return MemberGrant.builder()
                .id(memberGrantDTO.getId())
                .name(memberGrantDTO.getName())
                .description(memberGrantDTO.getDescription())
                .build();
    }

    public static MemberGrantDTO fromEntity(MemberGrant memberGrant) {
        return MemberGrantDTO.builder()
                .id(memberGrant.getId())
                .name(memberGrant.getName())
                .description(memberGrant.getDescription())
                .build();
    }
}
