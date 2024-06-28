package com.portfolio.simpleboard.repository.member.search;

import com.portfolio.simpleboard.dto.member.RoleOwnGrantDTO;
import com.portfolio.simpleboard.entity.MemberGrant;
import com.portfolio.simpleboard.entity.MemberRole;

import java.util.Set;

public interface RoleOwnGrantSearch {

    RoleOwnGrantDTO searchRoleOwnGrant(Long roleId);

    Set<MemberGrant> searchGrantEntities(Set<MemberRole> roleSet);
}
