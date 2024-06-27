package com.portfolio.simpleboard.repository.member.search;

import com.portfolio.simpleboard.dto.member.RoleOwnGrantDTO;
import com.portfolio.simpleboard.entity.*;
import com.querydsl.jpa.JPQLQuery;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class RoleOwnGrantSearchImpl extends QuerydslRepositorySupport implements RoleOwnGrantSearch{

    RoleOwnGrantSearchImpl()
    {
        super(RoleOwnGrant.class);
    }


    @Transactional
    @Override
    public RoleOwnGrantDTO searchRoleOwnGrant(Long roleId){

        QRoleOwnGrant roleOwnGrant = QRoleOwnGrant.roleOwnGrant;
        JPQLQuery<RoleOwnGrant> query = from(roleOwnGrant);

        QMemberRole memberRole = QMemberRole.memberRole;
        JPQLQuery<MemberRole> roleQuery = from(memberRole);

        QMemberGrant memberGrant = QMemberGrant.memberGrant;
        JPQLQuery<MemberGrant> grantQuery = from(memberGrant);

        roleQuery.where(memberRole.id.eq(roleId));
        MemberRole role = roleQuery.fetchFirst();
        if(role == null) {
            return null;
        }

        grantQuery.where(memberGrant.id.gt(0));
        List<MemberGrant> memberGrantList = grantQuery.fetch();

        query.where(roleOwnGrant.id.memberRole.eq(role));
        List<RoleOwnGrant> roleOwnGrantList = query.fetch();

        return RoleOwnGrantDTO.fromEntities(role, roleOwnGrantList, memberGrantList);
    }

}
