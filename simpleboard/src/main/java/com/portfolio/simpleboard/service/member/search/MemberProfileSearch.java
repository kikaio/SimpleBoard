package com.portfolio.simpleboard.service.member.search;


import com.portfolio.simpleboard.dto.member.MemberProfileDTO;
import com.portfolio.simpleboard.dto.pager.PageRequestDTO;
import com.portfolio.simpleboard.dto.pager.PageResponseDTO;

public interface MemberProfileSearch {
    PageResponseDTO<MemberProfileDTO> searchMemberProfile(PageRequestDTO pageRequestDTO);
}
