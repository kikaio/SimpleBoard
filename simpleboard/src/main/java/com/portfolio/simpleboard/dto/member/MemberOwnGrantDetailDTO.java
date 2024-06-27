package com.portfolio.simpleboard.dto.member;


import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@Builder
public class MemberOwnGrantDetailDTO {

    @Builder.Default
    private List<MemberGrantDTO> ownMemberGrantDTOS = new ArrayList<>();


    @Builder.Default
    private List<MemberGrantDTO> noneOwnMemberGrantDTOS = new ArrayList<>();

    public static MemberOwnGrantDetailDTO fromEntities(List<MemberGrantDTO> memberGrantDTOS, List<MemberOwnGrantDTO> memberOwnGrantDTOS) {
        var dto = MemberOwnGrantDetailDTO.builder()
                .build();
        for(var grant :memberGrantDTOS) {
            boolean find = memberOwnGrantDTOS.stream().anyMatch(ele->{
                return grant.getId().longValue() == ele.getMemberGrant().getId().longValue();
            });
            if(find) {
                dto.getOwnMemberGrantDTOS().add(grant);
            } else {
                dto.getNoneOwnMemberGrantDTOS().add(grant);
            }
        }
        return dto;
    }
}
