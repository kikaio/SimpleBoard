package com.portfolio.simpleboard.repository.member;

import com.portfolio.simpleboard.entity.MemberOwnRole;
import com.portfolio.simpleboard.repository.member.search.MemberOwnRoleSearch;
import com.portfolio.simpleboard.service.member.MemberOwnRoleService;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberOwnRoleRepository extends JpaRepository<MemberOwnRole, MemberOwnRole.MemberOwnRoleId> , MemberOwnRoleSearch {

}
