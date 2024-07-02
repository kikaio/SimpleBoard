package com.portfolio.simpleboard.service.member;


import com.portfolio.simpleboard.dto.member.MemberProfileDTO;
import com.portfolio.simpleboard.dto.pager.PageRequestDTO;
import com.portfolio.simpleboard.dto.pager.PageResponseDTO;
import com.portfolio.simpleboard.repository.member.MemberProfileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;


@Service
@Log4j2
@RequiredArgsConstructor
public class MemberProfileService {

    private final MemberProfileRepository memberProfileRepository;

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
