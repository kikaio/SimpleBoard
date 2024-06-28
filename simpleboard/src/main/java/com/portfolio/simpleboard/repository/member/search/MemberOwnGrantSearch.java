package com.portfolio.simpleboard.repository.member.search;

import com.portfolio.simpleboard.dto.member.MemberOwnGrantDetailDTO;
import com.portfolio.simpleboard.entity.MemberGrant;
import com.portfolio.simpleboard.entity.MemberProfile;

import java.util.List;
import java.util.Set;

public interface MemberOwnGrantSearch {

    MemberOwnGrantDetailDTO searchMemberOwnGrantDetailDTO(Long profileId);

    Set<MemberGrant> searchMemberOwnGrantEntities(MemberProfile memberProfile);

}
