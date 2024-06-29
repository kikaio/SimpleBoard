package com.portfolio.simpleboard.service.member;

import com.portfolio.simpleboard.dto.member.MemberGrantDTO;
import com.portfolio.simpleboard.dto.member.MemberRoleDTO;
import com.portfolio.simpleboard.dto.member.RoleOwnGrantDTO;
import com.portfolio.simpleboard.entity.MemberGrant;
import com.portfolio.simpleboard.entity.MemberRole;
import com.portfolio.simpleboard.entity.RoleOwnGrant;
import com.portfolio.simpleboard.repository.member.MemberGrantRepository;
import com.portfolio.simpleboard.repository.member.MemberRoleRepository;
import com.portfolio.simpleboard.repository.member.RoleOwnGrantRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class RoleOwnGrantService {

    private final MemberRoleRepository memberRoleRepository;
    private final MemberGrantRepository memberGrantRepository;
    private final RoleOwnGrantRepository roleOwnGrantRepository;

    public RoleOwnGrantDTO getRoleOwnGrant(Long id) {
        var dto = roleOwnGrantRepository.searchRoleOwnGrant(id);
        return dto;
    }

    @Transactional
    public boolean createRoleOwnGrant(Long roleId, MemberGrantDTO memberGrantDTO) {
        MemberRole memberRole = memberRoleRepository.findById(roleId).orElse(null);
        if(memberRole == null) {
            log.error("invalid request. role[%d] is not exist in repo".formatted(roleId));
            return false;
        }

        MemberGrant memberGrant = memberGrantRepository.findById(memberGrantDTO.getId()).orElse(null);
        if(memberGrant == null) {
            log.error("invalid request. grant[%d] is not exist in repo".formatted(memberGrantDTO.getId()));
            return false;
        }
        var targetId = new RoleOwnGrant.RoleOwnGrantId(memberGrant, memberRole);
        RoleOwnGrant roleOwnGrant = roleOwnGrantRepository.findById(targetId).orElse(null);
        if(roleOwnGrant != null) {
            log.error("role[%d] own grant[%d] is already exist ".formatted(roleId, memberGrant.getId()));
            return false;
        }
        RoleOwnGrant newRoleOwnGrant = RoleOwnGrant.builder()
                .id(targetId)
                .build()
        ;
        newRoleOwnGrant = roleOwnGrantRepository.save(newRoleOwnGrant);
        log.info("newRoleOwnGrant was saved. : %s".formatted(newRoleOwnGrant));
        return true;
    }
    @Transactional
    public boolean deleteRoleOwnGrant(Long roleId, MemberGrantDTO memberGrantDTO) {

        MemberRole memberRole = memberRoleRepository.findById(roleId).orElse(null);
        if(memberRole == null) {
            log.error("role[%d] is not exist".formatted(roleId));
            return false;
        }

        MemberGrant grant = memberGrantRepository.findById(memberGrantDTO.getId()).orElse(null);
        if(grant == null) {
            log.error("grant[%d] is not exist".formatted(memberGrantDTO.getId()));
            return false;
        }

        RoleOwnGrant.RoleOwnGrantId id = new RoleOwnGrant.RoleOwnGrantId(grant, memberRole);
        var target = roleOwnGrantRepository.findById(id).orElse(null);
        if(target == null) {
            log.error("this roleOwnGrant[%d-%d] is not exist.".formatted(roleId, memberGrantDTO.getId()));
            return false;
        }
        roleOwnGrantRepository.deleteById(id);
        return true;
    }
}
