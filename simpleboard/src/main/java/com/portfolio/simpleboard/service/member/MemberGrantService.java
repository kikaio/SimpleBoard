package com.portfolio.simpleboard.service.member;


import com.portfolio.simpleboard.dto.member.MemberGrantDTO;
import com.portfolio.simpleboard.entity.MemberGrant;
import com.portfolio.simpleboard.repository.member.MemberGrantRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class MemberGrantService {

    private final MemberGrantRepository memberGrantRepository;

    public List<MemberGrantDTO> getMemberGrants() {
        List<MemberGrant> entityList = memberGrantRepository.findAll();
        return entityList.stream().map(x->{
            return MemberGrantDTO.fromEntity(x);
        }).collect(Collectors.toList());
    }

    public MemberGrantDTO getMemberGrant(Long id) {
        var ret = memberGrantRepository.findById(id).orElse(null);
        if(ret == null) {
            return null;
        }
        return MemberGrantDTO.fromEntity(ret);
    }

    public boolean insertMemberGrant(MemberGrantDTO memberGrantDTO) {
        var entity = MemberGrantDTO.toEntity(memberGrantDTO);
        if(entity.getId() != null) {
            return false;
        }
        var alreadyExist = memberGrantRepository.findByName(memberGrantDTO.getName()).orElse(null);
        if(alreadyExist!= null) {
            return false;
        }

        entity = memberGrantRepository.save(entity);
        log.info("member grant inserted. : %s".formatted(entity));

        return true;
    }

    public boolean deleteMemberGrant(MemberGrantDTO memberGrantDTO) {
        Long id = memberGrantDTO.getId();
        var target = memberGrantRepository.findById(id).orElse(null);
        if(target == null)
            return false;
        memberGrantRepository.deleteById(id);
        return true;
    }

    public boolean updateMemberGrant(MemberGrantDTO memberGrantDTO) {
        var target = memberGrantRepository.findById(memberGrantDTO.getId()).orElse(null);
        if(target == null) {
            return false;
        }

        target.update(memberGrantDTO.getName(), memberGrantDTO.getDescription());
        log.info("member grant before update. : %s".formatted(target));
        target = memberGrantRepository.save(target);
        log.info("member grant updated. : %s".formatted(target));
        return true;
    }
}
