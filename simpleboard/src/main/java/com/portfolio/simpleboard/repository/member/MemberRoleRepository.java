package com.portfolio.simpleboard.repository.member;

import com.portfolio.simpleboard.entity.MemberRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRoleRepository extends JpaRepository<MemberRole, Long> {

    Optional<MemberRole> findByName(String name);
}
