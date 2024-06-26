package com.portfolio.simpleboard.service.member;

import com.portfolio.simpleboard.dto.member.MemberOwnRoleSearchDTO;
import com.portfolio.simpleboard.dto.member.MemberProfileDTO;
import com.portfolio.simpleboard.dto.pager.PageRequestDTO;
import com.portfolio.simpleboard.dto.pager.PageResponseDTO;
import com.portfolio.simpleboard.repository.member.MemberOwnRoleRepository;
import com.portfolio.simpleboard.repository.member.MemberProfileRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class MemberOwnRoleService {

    private final MemberOwnRoleRepository memberOwnRoleRepository;
    private final MemberProfileRepository memberProfileRepository;

    @Transactional
    public MemberOwnRoleSearchDTO searchMemberProfile(PageRequestDTO pageRequestDTO) {
        PageResponseDTO<MemberProfileDTO> pageResponseDTO
                = memberProfileRepository.searchMemberProfile(pageRequestDTO);
        return MemberOwnRoleSearchDTO.fromEntities(pageRequestDTO, pageResponseDTO);
    }
}
