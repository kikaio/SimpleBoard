package com.portfolio.simpleboard.dto.member;


import com.portfolio.simpleboard.entity.MemberGrant;
import com.portfolio.simpleboard.entity.MemberOwnGrant;
import com.portfolio.simpleboard.entity.MemberProfile;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class MemberOwnGrantDTO {

    private MemberProfile memberProfile;
    private MemberGrant memberGrant;

    public static MemberOwnGrantDTO fromEntity(MemberOwnGrant memberOwnGrant) {
        return MemberOwnGrantDTO.builder()
                .memberProfile(memberOwnGrant.getMemberOwnGrantId().getMemberProfile())
                .memberGrant(memberOwnGrant.getMemberOwnGrantId().getMemberGrant())
                .build();
    }

    public static MemberOwnGrant toEntity(MemberOwnGrantDTO memberOwnGrantDTO) {

        var id = new MemberOwnGrant.MemberOwnGrantId(memberOwnGrantDTO.getMemberProfile(), memberOwnGrantDTO.getMemberGrant());
        return MemberOwnGrant.builder()
                .memberOwnGrantId(id)
                .build();
    }
}
