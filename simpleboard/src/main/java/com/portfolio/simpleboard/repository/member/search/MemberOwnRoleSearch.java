package com.portfolio.simpleboard.repository.member.search;


import com.portfolio.simpleboard.dto.member.MemberOwnRoleDetailDTO;

public interface MemberOwnRoleSearch {
    MemberOwnRoleDetailDTO searchMemberOwnRoleDetail(Long profileId);
}
