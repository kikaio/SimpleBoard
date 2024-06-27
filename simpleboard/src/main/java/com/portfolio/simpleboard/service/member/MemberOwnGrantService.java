package com.portfolio.simpleboard.service.member;


import com.portfolio.simpleboard.dto.member.MemberGrantDTO;
import com.portfolio.simpleboard.dto.member.MemberOwnGrantDTO;
import com.portfolio.simpleboard.dto.member.MemberOwnGrantDetailDTO;
import com.portfolio.simpleboard.entity.MemberOwnGrant;
import com.portfolio.simpleboard.entity.MemberProfile;
import com.portfolio.simpleboard.repository.member.MemberGrantRepository;
import com.portfolio.simpleboard.repository.member.MemberOwnGrantRepository;
import com.portfolio.simpleboard.repository.member.MemberProfileRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class MemberOwnGrantService {
    private final MemberOwnGrantRepository memberOwnGrantRepository;
    private final MemberProfileRepository memberProfileRepository;
    private final MemberGrantRepository memberGrantRepository;


    @Transactional
    public MemberOwnGrantDetailDTO searchMemberOwnGrantDetailDTO(Long profileId) {
        return memberOwnGrantRepository.searchMemberOwnGrantDetailDTO(profileId);
    }

    @Transactional
    public boolean createMemberOwnGrant(Long profileId, MemberGrantDTO memberGrantDTO) {
        var profile = memberProfileRepository.findById(profileId).orElse(null);
        if(profile == null) {
            log.error("this profile[%d] not exists".formatted(profileId));
            return false;
        }

        var memberGrant = memberGrantRepository.findById(memberGrantDTO.getId()).orElse(null);
        if(memberGrant == null) {
            log.error("grant[%d] is not exist".formatted(memberGrantDTO.getId()));
            return false;
        }
        var id = new MemberOwnGrant.MemberOwnGrantId(profile, memberGrant);
        var ownGrant = memberOwnGrantRepository.findById(id).orElse(null);
        if(ownGrant != null) {
            log.error("own grant[%d].is already exist".formatted(memberGrant.getId()));
            return false;
        }
        var memberOwnGrant = MemberOwnGrant.builder()
                .memberOwnGrantId(id)
                .build();

        memberOwnGrant = memberOwnGrantRepository.save(memberOwnGrant);
        return true;
    }

    @Transactional
    public boolean deleteMemberOwnGrant(Long profileId, MemberGrantDTO memberGrantDTO) {
        var profile = memberProfileRepository.findById(profileId).orElse(null);
        if(profile == null) {
            log.error("this profile[%d] not exists".formatted(profileId));
            return false;
        }

        var memberGrant = memberGrantRepository.findById(memberGrantDTO.getId()).orElse(null);
        if(memberGrant == null) {
            log.error("grant[%d] is not exist".formatted(memberGrantDTO.getId()));
            return false;
        }
        var id = new MemberOwnGrant.MemberOwnGrantId(profile, memberGrant);
        var ownGrant = memberOwnGrantRepository.findById(id).orElse(null);
        if(ownGrant == null) {
            log.error("own grant[%d].is not exist".formatted(memberGrant.getId()));
            return false;
        }
        memberOwnGrantRepository.deleteById(id);
        return true;
    }

}
