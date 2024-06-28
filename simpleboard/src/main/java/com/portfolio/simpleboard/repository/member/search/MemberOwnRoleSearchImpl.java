package com.portfolio.simpleboard.repository.member.search;

import com.portfolio.simpleboard.dto.member.MemberOwnRoleDTO;
import com.portfolio.simpleboard.dto.member.MemberOwnRoleDetailDTO;
import com.portfolio.simpleboard.dto.member.MemberRoleDTO;
import com.portfolio.simpleboard.entity.*;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQuery;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class MemberOwnRoleSearchImpl extends QuerydslRepositorySupport implements  MemberOwnRoleSearch{

    public MemberOwnRoleSearchImpl() {
        super(MemberOwnRole.class);
    }

    @Transactional
    @Override
    public MemberOwnRoleDetailDTO searchMemberOwnRoleDetail(Long profileId) {
        QMemberProfile memberProfile = QMemberProfile.memberProfile;
        JPQLQuery<MemberProfile> memberProfileJPQLQuery = from(memberProfile);
        memberProfileJPQLQuery.where(memberProfile.id.eq(profileId));
        var profile = memberProfileJPQLQuery.fetchFirst();

        QMemberOwnRole memberOwnRole = QMemberOwnRole.memberOwnRole;
        JPQLQuery<MemberOwnRole> query = from(memberOwnRole);

        query.where(memberOwnRole.memberOwnRoleId.memberProfile.eq(profile));
        List<MemberOwnRole>  memberOwnRoles = query.fetch();
        var dtoLsit = memberOwnRoles.stream().map(ele -> {
            return MemberOwnRoleDTO.fromEntity(ele);
        }).toList();

        QMemberRole memberRole = QMemberRole.memberRole;
        JPQLQuery<MemberRole> memberRoleJPQLQuery = from(memberRole);
        var memberRoles = memberRoleJPQLQuery.fetch();
        var roleDTOList = memberRoles.stream().map(role->{
            return MemberRoleDTO.fromEntity(role);
        }).toList();

        return MemberOwnRoleDetailDTO.fromEntities(roleDTOList, dtoLsit);
    }

    @Override
    public Set<MemberRole> searchMemberOwnRoleEntities(MemberProfile memberProfile) {

        QMemberOwnRole memberOwnRole = QMemberOwnRole.memberOwnRole;
        JPQLQuery<MemberOwnRole> memberOwnRoleJPQLQuery = from(memberOwnRole);

        memberOwnRoleJPQLQuery.where(memberOwnRole.memberOwnRoleId.memberProfile.eq(memberProfile));
        var entities = memberOwnRoleJPQLQuery.fetch();
        return entities.stream().map(ele->{
            return ele.getMemberOwnRoleId().getMemberRole();
        }).collect(Collectors.toSet());
    }

}
