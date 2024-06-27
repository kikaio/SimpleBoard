package com.portfolio.simpleboard.repository.member.search;

import com.portfolio.simpleboard.dto.member.MemberOwnGrantDetailDTO;

public interface MemberOwnGrantSearch {

    MemberOwnGrantDetailDTO searchMemberOwnGrantDetailDTO(Long profileId);

}
