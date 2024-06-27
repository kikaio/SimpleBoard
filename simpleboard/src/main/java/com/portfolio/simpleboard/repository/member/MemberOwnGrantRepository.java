package com.portfolio.simpleboard.repository.member;

import com.portfolio.simpleboard.dto.member.MemberOwnGrantDetailDTO;
import com.portfolio.simpleboard.entity.MemberOwnGrant;
import com.portfolio.simpleboard.repository.member.search.MemberOwnGrantSearch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberOwnGrantRepository extends JpaRepository<MemberOwnGrant, MemberOwnGrant.MemberOwnGrantId> , MemberOwnGrantSearch {
}
