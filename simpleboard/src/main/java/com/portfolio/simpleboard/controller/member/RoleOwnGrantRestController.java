package com.portfolio.simpleboard.controller.member;

import com.portfolio.simpleboard.dto.member.MemberGrantDTO;
import com.portfolio.simpleboard.dto.member.RoleOwnGrantDTO;
import com.portfolio.simpleboard.service.member.RoleOwnGrantService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Log4j2
@RequestMapping("/members/roles/grants")
@RequiredArgsConstructor
public class RoleOwnGrantRestController {

    private final RoleOwnGrantService roleOwnGrantService;

    @GetMapping("")
    public RoleOwnGrantDTO getRoleOwnGrantList(@RequestParam Long id) {
        var ret = roleOwnGrantService.getRoleOwnGrant(id);
        return ret;
    }

    @PostMapping(value = "/{roleId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public boolean createRoleOwnGrant(@PathVariable Long roleId, @RequestBody MemberGrantDTO memberGrantDTO) {
        var ret = roleOwnGrantService.createRoleOwnGrant(roleId, memberGrantDTO);
        return ret;
    }

    @DeleteMapping(value="/{roleId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public boolean deleteRoleOwnGrant(@PathVariable Long roleId, @RequestBody MemberGrantDTO memberGrantDTO) {
        var ret = roleOwnGrantService.deleteRoleOwnGrant(roleId, memberGrantDTO);
        return ret;
    }
}
