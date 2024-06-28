package com.portfolio.simpleboard.repository.member.search;

import com.portfolio.simpleboard.dto.member.*;
import com.portfolio.simpleboard.entity.*;
import com.querydsl.jpa.JPQLQuery;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Log4j2
public class MemberOwnGrantSearchImpl extends QuerydslRepositorySupport implements MemberOwnGrantSearch {

    public  MemberOwnGrantSearchImpl(){
        super(MemberOwnGrant.class);
    }

    @Override
    public MemberOwnGrantDetailDTO searchMemberOwnGrantDetailDTO(Long profileId) {

        QMemberProfile memberProfile = QMemberProfile.memberProfile;
        JPQLQuery<MemberProfile> memberProfileJPQLQuery = from(memberProfile);

        memberProfileJPQLQuery.where(memberProfile.id.eq(profileId));
        var profile = memberProfileJPQLQuery.fetchFirst();
        if(profile == null) {
            log.error("this profile[%d] not exists".formatted(profileId));
            return null;
        }

        QMemberGrant memberGrant = QMemberGrant.memberGrant;
        JPQLQuery<MemberGrant> memberGrantJPQLQuery = from(memberGrant);
        memberGrantJPQLQuery.where(memberGrant.id.gt(0L));
        var roleList = memberGrantJPQLQuery.fetch();

        QMemberOwnGrant memberOwnGrant = QMemberOwnGrant.memberOwnGrant;
        JPQLQuery<MemberOwnGrant> memberOwnGrantJPQLQuery = from(memberOwnGrant);

        memberOwnGrantJPQLQuery.where(memberOwnGrant.memberOwnGrantId.memberProfile.eq(profile));
        var memberOwnGrantList = memberOwnGrantJPQLQuery.fetch();

        var grantDTOList = roleList.stream()
                .map(ele->{
                    return MemberGrantDTO.fromEntity(ele);
                })
                .toList();

        List<MemberOwnGrantDTO> ownDTOList = memberOwnGrantList.stream()
                .map(ele->{
                    return MemberOwnGrantDTO.fromEntity(ele);
                })
                .toList();

        return MemberOwnGrantDetailDTO.fromEntities(grantDTOList, ownDTOList);
    }


    @Override
    public Set<MemberGrant> searchMemberOwnGrantEntities(MemberProfile memberProfile) {

        QMemberOwnGrant memberOwnGrant = QMemberOwnGrant.memberOwnGrant;
        JPQLQuery<MemberOwnGrant> memberOwnGrantJPQLQuery = from(memberOwnGrant);

        memberOwnGrantJPQLQuery.where(memberOwnGrant.memberOwnGrantId.memberProfile.eq(memberProfile));
        List<MemberOwnGrant> entities = memberOwnGrantJPQLQuery.fetch();

        return entities.stream().map(ele->{
            return ele.getMemberOwnGrantId().getMemberGrant();
        }).collect(Collectors.toSet());
    }

}
