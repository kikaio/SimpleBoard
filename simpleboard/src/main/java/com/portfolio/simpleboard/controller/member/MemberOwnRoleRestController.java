package com.portfolio.simpleboard.controller.member;

import com.portfolio.simpleboard.dto.member.MemberOwnRoleSearchDTO;
import com.portfolio.simpleboard.dto.member.MemberProfileDTO;
import com.portfolio.simpleboard.dto.pager.PageRequestDTO;
import com.portfolio.simpleboard.dto.pager.PageResponseDTO;
import com.portfolio.simpleboard.service.member.MemberOwnRoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@Log4j2
@RequestMapping("/members/own/roles")
@RequiredArgsConstructor
public class MemberOwnRoleRestController {

    private final MemberOwnRoleService memberOwnRoleService;

    @GetMapping("")
    public MemberOwnRoleSearchDTO searchMemberProfile(PageRequestDTO pageRequestDTO) {
        return memberOwnRoleService.searchMemberProfile(pageRequestDTO);
    }
}
