package com.portfolio.simpleboard.repository.member;

import com.portfolio.simpleboard.entity.MemberProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberProfileRepository extends JpaRepository<MemberProfile, Long> {
}
