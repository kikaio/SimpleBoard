package com.portfolio.simpleboard.controller.member;

import com.portfolio.simpleboard.dto.member.MemberProfileDTO;
import com.portfolio.simpleboard.dto.pager.PageRequestDTO;
import com.portfolio.simpleboard.dto.pager.PageResponseDTO;
import com.portfolio.simpleboard.service.member.MemberProfileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/members/profiles")
public class MemberProfileRestController {

    private final MemberProfileService memberProfileService;


    @GetMapping("")
    public PageResponseDTO<MemberProfileDTO> searchMemberProfileList(PageRequestDTO pageRequestDTO) {
        return memberProfileService.searchMemberProfileList(pageRequestDTO);
    }
}
