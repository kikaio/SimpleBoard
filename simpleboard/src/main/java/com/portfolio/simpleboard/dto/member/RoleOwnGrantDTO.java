package com.portfolio.simpleboard.dto.member;


import com.portfolio.simpleboard.entity.MemberGrant;
import com.portfolio.simpleboard.entity.MemberRole;
import com.portfolio.simpleboard.entity.QRoleOwnGrant;
import com.portfolio.simpleboard.entity.RoleOwnGrant;
import lombok.*;
import lombok.extern.log4j.Log4j2;

import java.util.ArrayList;
import java.util.List;


@ToString
@Log4j2
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class RoleOwnGrantDTO {

    private MemberRoleDTO memberRoleDTO;

    @Builder.Default
    private List<MemberGrantDTO> noneOwnGrantList = new ArrayList<>();
    @Builder.Default
    private List<MemberGrantDTO> ownGrantList = new ArrayList<>();

    static public RoleOwnGrantDTO fromEntities(MemberRole memberRole, List<RoleOwnGrant> rogList, List<MemberGrant> allGrantList) {
        var dto = RoleOwnGrantDTO.builder()
                .memberRoleDTO(MemberRoleDTO.fromEntity(memberRole))
                .build()
                ;

        for(MemberGrant grant : allGrantList) {

            boolean find = rogList.stream().anyMatch(ele->{
                return ele.getRoleGrantId().getMemberGrant() == grant;
            });
            if(find) {
                dto.getOwnGrantList().add(MemberGrantDTO.fromEntity(grant));
            } else {
                dto.getNoneOwnGrantList().add(MemberGrantDTO.fromEntity(grant));
            }
        }
        return dto;
    }
}
