package com.portfolio.simpleboard.controller.member;


import com.portfolio.simpleboard.dto.member.MemberRoleDTO;
import com.portfolio.simpleboard.service.member.MemberRoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/members/roles")
@RequiredArgsConstructor
@Log4j2
public class MemberRoleRestController {

    private final MemberRoleService memberRoleService;

    @GetMapping("")
    public List<MemberRoleDTO> getMemberRoles() {
        var memberDTOList = memberRoleService.getRoleDTOList();
        return new ArrayList<>();
    }

    @PostMapping(value="", consumes = MediaType.APPLICATION_JSON_VALUE)
    public boolean createMemberRole(@RequestBody MemberRoleDTO dto) {
        var ret = memberRoleService.createMemberRole(dto);
        return ret;
    }

    @PutMapping("/{id}")
    public boolean updateMemberRole(@PathVariable(required = true) Long id,  @RequestBody MemberRoleDTO dto) {
        if(id != dto.getId()) {
            log.error("bad request. id[%d] and dto's id[%d] not matched".formatted(id, dto.getId()));
            return false;
        }

        var ret = memberRoleService.updateMemberRole(dto);
        return ret;
    }

    @DeleteMapping("/{id}")
    public boolean deleteMemberRole(@PathVariable Long id, @RequestBody MemberRoleDTO dto) {
        if(id != dto.getId()) {
            log.error("bad request. id[%d], dto's id[%d] must match".formatted(id, dto.getId()));
            return false;
        }
        var ret = memberRoleService.deleteMemberRole(dto);
        return ret;
    }
}
