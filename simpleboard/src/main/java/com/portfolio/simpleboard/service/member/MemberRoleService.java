package com.portfolio.simpleboard.service.member;


import com.portfolio.simpleboard.dto.member.MemberRoleDTO;
import com.portfolio.simpleboard.repository.member.MemberRoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class MemberRoleService {

    private final MemberRoleRepository memberRoleRepository;

    public List<MemberRoleDTO> getRoleDTOList() {
        var entities = memberRoleRepository.findAll();
        var ret = entities.stream().map(entity->{
            return MemberRoleDTO.fromEntity(entity);
        }).toList();
        return ret;
    }

    public boolean createMemberRole(MemberRoleDTO dto) {
        var entity = MemberRoleDTO.toEntity(dto);
        var ori = memberRoleRepository.findByName(dto.getName()).orElse(null);
        if(ori != null) {
            log.error("this role[%s] already exist.".formatted(dto.getName()));
            return false;
        }
        memberRoleRepository.save(entity);
        log.info("new role created. %s".formatted(dto));
        return true;
    }

    public boolean updateMemberRole(MemberRoleDTO dto) {
        var ori = memberRoleRepository.findById(dto.getId()).orElse(null);
        if(ori == null){
            log.error("bad request. not exist member role[%d - %s].".formatted(dto.getId(), dto.getName()));
            return false;
        }
        ori.updateInfo(dto.getName(), dto.getDescription());
        ori = memberRoleRepository.save(ori);
        log.info("role[%d] [%s]][%s] was updated".formatted(ori.getId(), ori.getName(), ori.getDescription()));
        return true;
    }

    public boolean deleteMemberRole(MemberRoleDTO dto) {
        var ori = memberRoleRepository.findById(dto.getId()).orElse(null);
        if(ori == null) {
            log.error("role[%d] [%s][%s]noe exist.".formatted(dto.getId(), dto.getName(), dto.getDescription()));
            return false;
        }
        memberRoleRepository.deleteById(ori.getId());
        log.info("role deleted. %s".formatted(ori));
        return true;
    }
}
