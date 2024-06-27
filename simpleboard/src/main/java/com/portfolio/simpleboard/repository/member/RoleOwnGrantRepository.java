package com.portfolio.simpleboard.repository.member;

import com.portfolio.simpleboard.entity.RoleOwnGrant;
import com.portfolio.simpleboard.repository.member.search.RoleOwnGrantSearch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleOwnGrantRepository extends JpaRepository<RoleOwnGrant, RoleOwnGrant.RoleOwnGrantId>, RoleOwnGrantSearch {
}
