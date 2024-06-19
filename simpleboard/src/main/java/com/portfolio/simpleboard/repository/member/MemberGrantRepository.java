package com.portfolio.simpleboard.repository.member;

import com.portfolio.simpleboard.entity.MemberGrant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberGrantRepository extends JpaRepository<MemberGrant, Long> {

    Optional<MemberGrant> findByName(String name);
}
