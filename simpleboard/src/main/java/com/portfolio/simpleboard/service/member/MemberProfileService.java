package com.portfolio.simpleboard.service.member;


import com.portfolio.simpleboard.dto.member.MemberProfileDTO;
import com.portfolio.simpleboard.dto.member.MemberProfileDetailDTO;
import com.portfolio.simpleboard.dto.pager.PageRequestDTO;
import com.portfolio.simpleboard.dto.pager.PageResponseDTO;
import com.portfolio.simpleboard.repository.member.MemberOwnGrantRepository;
import com.portfolio.simpleboard.repository.member.MemberOwnRoleRepository;
import com.portfolio.simpleboard.repository.member.MemberProfileRepository;
import com.portfolio.simpleboard.repository.member.RoleOwnGrantRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;


@Service
@Log4j2
@RequiredArgsConstructor
public class MemberProfileService {

    private final MemberProfileRepository memberProfileRepository;
    private final MemberOwnRoleRepository memberOwnRoleRepository;
    private final MemberOwnGrantRepository memberOwnGrantRepository;
    private final RoleOwnGrantRepository roleOwnGrantRepository;


    public PageResponseDTO<MemberProfileDTO> searchMemberProfileList(PageRequestDTO pageRequestDTO) {
        return memberProfileRepository.searchMemberProfile(pageRequestDTO);
    }

    public MemberProfileDTO readOne(Long profileId) {
        var profile = memberProfileRepository.findById(profileId).orElse(null);
        if(profile == null) {
            return null;
        }
        return MemberProfileDTO.fromEntity(profile);
    }

    @Transactional
    public MemberProfileDetailDTO readOneToDetailDTO(Long profileId) {
        var profile = memberProfileRepository.findById(profileId).orElse(null);
        if(profile == null) {
            return null;
        }
        var grantSet = memberOwnGrantRepository.searchMemberOwnGrantEntities(profile);
        var roleSet = memberOwnRoleRepository.searchMemberOwnRoleEntities(profile);
        var grantFromRole = roleOwnGrantRepository.searchGrantEntities(roleSet);

        grantSet.addAll(grantFromRole);

        grantSet.forEach(ele->{
            profile.getAuthorities().add(new SimpleGrantedAuthority(ele.getName()));
        });
        roleSet.forEach(ele->{
            profile.getAuthorities().add(new SimpleGrantedAuthority(ele.getName()));
        });

        return MemberProfileDetailDTO.fromEntity(profile);
    }

    public boolean checkNicknameDuplication(Long profileId, String nickname) {
        var profile = memberProfileRepository.findByNickname(nickname).orElse(null);
        if(profile != null) {
            if(profileId.longValue() == profile.getId().longValue()) {
                return false;
            } else {
                return true;
            }
        }
        return false;
    }
}
