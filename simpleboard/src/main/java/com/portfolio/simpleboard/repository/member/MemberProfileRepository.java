package com.portfolio.simpleboard.repository.member;

import com.portfolio.simpleboard.entity.MemberProfile;
import com.portfolio.simpleboard.service.member.search.MemberProfileSearch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberProfileRepository extends JpaRepository<MemberProfile, Long>, MemberProfileSearch {
}
