package com.portfolio.simpleboard.dto.member;


import com.portfolio.simpleboard.entity.MemberOwnRole;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access=AccessLevel.PROTECTED)
public class MemberOwnRoleDetailDTO {

    @Builder.Default
    private List<MemberRoleDTO> roleDTOList = new ArrayList<>();

    @Builder.Default
    private List<MemberRoleDTO> noneRoleDTOList = new ArrayList<>();

    public static MemberOwnRoleDetailDTO fromEntities(List<MemberRoleDTO> allRoleList, List<MemberOwnRoleDTO> ownRoleDTOList) {
        var ret = MemberOwnRoleDetailDTO.builder()
                .build();

        for(var role : allRoleList) {
            boolean find = ownRoleDTOList.stream().anyMatch(own->{
                return own.getMemberRoleDTO().getId().longValue() == role.getId().longValue();
            });

            if(find) {
                ret.getRoleDTOList().add(role);
            } else {
                ret.getNoneRoleDTOList().add(role);
            }
        }
        return ret;
    }
}
