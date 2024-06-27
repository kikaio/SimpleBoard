package com.portfolio.simpleboard.service.member;

import com.portfolio.simpleboard.dto.member.MemberOwnRoleDetailDTO;
import com.portfolio.simpleboard.dto.member.MemberProfileDTO;
import com.portfolio.simpleboard.dto.member.MemberRoleDTO;
import com.portfolio.simpleboard.dto.pager.PageRequestDTO;
import com.portfolio.simpleboard.dto.pager.PageResponseDTO;
import com.portfolio.simpleboard.entity.MemberOwnRole;
import com.portfolio.simpleboard.repository.member.MemberOwnRoleRepository;
import com.portfolio.simpleboard.repository.member.MemberProfileRepository;
import com.portfolio.simpleboard.repository.member.MemberRoleRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@Log4j2
@RequiredArgsConstructor
public class MemberOwnRoleService {

    private final MemberOwnRoleRepository memberOwnRoleRepository;
    private final MemberProfileRepository memberProfileRepository;
    private final MemberRoleRepository memberRoleRepository;

    public MemberOwnRoleDetailDTO searchMemberOwnRoleDetail(Long profileId) {
        return memberOwnRoleRepository.searchMemberOwnRoleDetail(profileId);
    }

    @Transactional
    public boolean createMemberOwnRole(Long profileId, MemberRoleDTO memberRoleDTO) {
        var profile = memberProfileRepository.findById(profileId).orElse(null);
        if(profile == null) {
            log.error("profile[%d] is not exist".formatted(profileId));
            return false;
        }

        var role = memberRoleRepository.findById(memberRoleDTO.getId()).orElse(null);
        if(role == null) {
            log.error("invalid role. [%d] is not exist".formatted(memberRoleDTO.getId()));
            return false;
        }


        var id = new MemberOwnRole.MemberOwnRoleId(profile, role);
        var ori = memberOwnRoleRepository.findById(id).orElse(null);
        if(ori != null) {
            log.error("role[%d][%s] is already exist".formatted(memberRoleDTO.getId(), memberRoleDTO.getName()));
            return false;
        }

        var memberOwnRole = MemberOwnRole.builder()
                .memberOwnRoleId(id)
                .build();
        log.info("pre create : %s".formatted(memberOwnRole));
        memberOwnRole = memberOwnRoleRepository.save(memberOwnRole);
        log.info("after create : %s".formatted(memberOwnRole));

        return true;
    }

    @Transactional
    public boolean deleteMemberOwnRole(Long profileId, MemberRoleDTO memberRoleDTO) {
        var profile = memberProfileRepository.findById(profileId).orElse(null);
        if(profile == null) {
            log.error("profile[%d] is not exist".formatted(profileId));
            return false;
        }

        var role = memberRoleRepository.findById(memberRoleDTO.getId()).orElse(null);
        if(role == null) {
            log.error("invalid role. [%d] is not exist".formatted(memberRoleDTO.getId()));
            return false;
        }


        var id = new MemberOwnRole.MemberOwnRoleId(profile, role);
        var ori = memberOwnRoleRepository.findById(id).orElse(null);
        if(ori == null) {
            log.error("role[%d][%s] is not exist".formatted(memberRoleDTO.getId(), memberRoleDTO.getName()));
            return false;
        }
        memberOwnRoleRepository.deleteById(id);
        return true;
    }
}
