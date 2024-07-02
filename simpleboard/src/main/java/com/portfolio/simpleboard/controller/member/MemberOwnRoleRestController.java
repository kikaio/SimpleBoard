package com.portfolio.simpleboard.controller.member;

import com.portfolio.simpleboard.dto.member.MemberOwnRoleDetailDTO;
import com.portfolio.simpleboard.dto.member.MemberRoleDTO;
import com.portfolio.simpleboard.service.member.MemberOwnRoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

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
    public boolean createMemberOwnRole(@PathVariable Long profileId, @RequestBody MemberRoleDTO memberRoleDTO, Principal principal) {
        var ret = memberOwnRoleService.createMemberOwnRole(profileId, memberRoleDTO);
        return ret;
    }

    @DeleteMapping("/{profileId}")
    public boolean deleteMemberOwnRole(@PathVariable Long profileId, @RequestBody MemberRoleDTO memberRoleDTO, Principal principal) {
        var ret = memberOwnRoleService.deleteMemberOwnRole(profileId, memberRoleDTO);
        return ret;
    }
}
