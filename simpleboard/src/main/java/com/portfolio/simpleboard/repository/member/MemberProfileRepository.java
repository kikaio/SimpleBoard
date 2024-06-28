package com.portfolio.simpleboard.repository.member;

import com.portfolio.simpleboard.entity.MemberProfile;
import com.portfolio.simpleboard.repository.member.search.MemberProfileSearch;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberProfileRepository extends JpaRepository<MemberProfile, Long>, MemberProfileSearch {

    public Optional<MemberProfile> findByNickname(String nickname);
}
