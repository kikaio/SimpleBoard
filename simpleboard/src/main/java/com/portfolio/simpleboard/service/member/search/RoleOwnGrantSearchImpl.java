package com.portfolio.simpleboard.service.member.search;

import com.portfolio.simpleboard.dto.member.RoleOwnGrantDTO;
import com.portfolio.simpleboard.entity.*;
import com.querydsl.core.BooleanBuilder;
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
    public RoleOwnGrantDTO searchRoleOwnGrant(Long id){

        QRoleOwnGrant roleOwnGrant = QRoleOwnGrant.roleOwnGrant;
        JPQLQuery query = from(roleOwnGrant);

        QMemberRole memberRole = QMemberRole.memberRole;
        JPQLQuery roleQuery = from(memberRole);

        QMemberGrant memberGrant = QMemberGrant.memberGrant;
        JPQLQuery grantQuery = from(memberGrant);

        roleQuery.where(memberRole.id.eq(id));
        List<MemberRole> roleList = roleQuery.fetch();
        if(roleList.size() == 0) {
            return null;
        }

        grantQuery.where(memberGrant.id.gt(0));
        List<MemberGrant> memberGrantList = grantQuery.fetch();

        query.where(roleOwnGrant.roleGrantId.memberRole.eq(roleList.get(0)));
        List<RoleOwnGrant> roleOwnGrantList = query.fetch();

        return RoleOwnGrantDTO.fromEntities(roleList.get(0), roleOwnGrantList, memberGrantList);
    }

}
