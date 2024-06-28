package com.portfolio.simpleboard.repository.member.search;


import com.portfolio.simpleboard.dto.member.MemberOwnRoleDetailDTO;
import com.portfolio.simpleboard.entity.MemberProfile;
import com.portfolio.simpleboard.entity.MemberRole;

import java.util.List;
import java.util.Set;

public interface MemberOwnRoleSearch {
    MemberOwnRoleDetailDTO searchMemberOwnRoleDetail(Long profileId);

    Set<MemberRole> searchMemberOwnRoleEntities(MemberProfile memberProfile);
}
