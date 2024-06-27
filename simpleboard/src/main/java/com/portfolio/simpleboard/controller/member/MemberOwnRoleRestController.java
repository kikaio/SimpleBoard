package com.portfolio.simpleboard.controller.member;

import com.portfolio.simpleboard.dto.member.MemberOwnRoleDetailDTO;
import com.portfolio.simpleboard.dto.member.MemberRoleDTO;
import com.portfolio.simpleboard.service.member.MemberOwnRoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

@RestController
@Log4j2
@RequestMapping("/members/own/roles")
@RequiredArgsConstructor
public class MemberOwnRoleRestController {

    private final MemberOwnRoleService memberOwnRoleService;

    @GetMapping("")
    public MemberOwnRoleDetailDTO searchMemberOwnRoleDetail(@RequestParam(name="profileId", required = true) Long profileId) {

        return memberOwnRoleService.searchMemberOwnRoleDetail(profileId);
    }

    @PostMapping("/{profileId}")
    public boolean createMemberOwnRole(@PathVariable Long profileId, @RequestBody MemberRoleDTO memberRoleDTO) {
        return memberOwnRoleService.createMemberOwnRole(profileId, memberRoleDTO);
    }

    @DeleteMapping("/{profileId}")
    public boolean deleteMemberOwnRole(@PathVariable Long profileId, @RequestBody MemberRoleDTO memberRoleDTO) {
        return memberOwnRoleService.deleteMemberOwnRole(profileId, memberRoleDTO);
    }
}
