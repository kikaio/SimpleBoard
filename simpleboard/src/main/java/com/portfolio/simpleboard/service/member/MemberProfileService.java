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

    public boolean modifyMemberProfileDetail(MemberProfileDetailDTO dto) {
        var memberProfile = memberProfileRepository.findById(dto.getId()).orElse(null);
        if(memberProfile == null) {
            log.error("modifyMemberProfileDetail : not exist memberProfile[%d]".formatted(dto.getId()));
            return false;
        }
        memberProfile.modifyDetail(MemberProfileDetailDTO.toEntity(dto));
        memberProfile = memberProfileRepository.save(memberProfile);
        return true;
    }

    public boolean modifyMemberProfileIsDel(Long profileId, boolean isDel) {
        var profile = memberProfileRepository.findById(profileId).orElse(null);
        if(profile == null) {
            log.error("member profile[%d] not exist".formatted(profileId));
            return false;
        }
        profile.modifyIsDel(isDel);
        profile = memberProfileRepository.save(profile);
        return true;
    }

    public boolean modifyMemberProfileIsAccountNonExpired(Long profileId, boolean isAccountNonExpired) {

        var profile = memberProfileRepository.findById(profileId).orElse(null);
        if(profile == null) {
            log.error("member profile[%d] not exist".formatted(profileId));
            return false;
        }
        profile.modifyIsAccountNonExpired(isAccountNonExpired);
        profile = memberProfileRepository.save(profile);
        return true;
    }

    public boolean modifyMemberProfileIsNonLocked(Long profileId, boolean isAccountNonLocked) {

        var profile = memberProfileRepository.findById(profileId).orElse(null);
        if(profile == null) {
            log.error("member profile[%d] not exist".formatted(profileId));
            return false;
        }
        profile.modifyIsAccountNonLocked(isAccountNonLocked);
        profile = memberProfileRepository.save(profile);
        return true;
    }

    public boolean modifyMemberProfileIsNonExpired(Long profileId, boolean isCredentialsNonExpired) {

        var profile = memberProfileRepository.findById(profileId).orElse(null);
        if(profile == null) {
            log.error("member profile[%d] not exist".formatted(profileId));
            return false;
        }
        profile.modifyIsCredentialsNonExpired(isCredentialsNonExpired);
        profile = memberProfileRepository.save(profile);
        return true;
    }

    public boolean modifyMemberProfileIsEnabled(Long profileId, boolean isEnabled) {

        var profile = memberProfileRepository.findById(profileId).orElse(null);
        if(profile == null) {
            log.error("member profile[%d] not exist".formatted(profileId));
            return false;
        }
        profile.modifyIsEnabled(isEnabled);
        profile = memberProfileRepository.save(profile);
        return true;
    }
}
