package com.portfolio.simpleboard.controller.member;


import com.portfolio.simpleboard.dto.member.MemberProfileDetailDTO;
import com.portfolio.simpleboard.service.member.MemberProfileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/members/profiles")
@RequiredArgsConstructor
public class MemberProfileController {

    private final MemberProfileService memberProfileService;

    @PreAuthorize("hasRole('ADMIN') or hasAuthority('PROFILE_LIST_READ')")
    @GetMapping("/list")
    public String getMemberProfileListPage() {
        return "/memberProfile/list";
    }

    @PreAuthorize("hasRole('ADMIN') or (hasAuthority('PROFILE_READ') or principal.id==#profileId)")
    @GetMapping("/detail")
    public String getMemberProfileDetailPage(@RequestParam Long profileId, Model model) {
        var profileDetailDTO = memberProfileService.readOneToDetailDTO(profileId);
        if(profileDetailDTO == null) {
            model.addAttribute("msg", "not exist profile");
            return "/error/simple";
        }
        model.addAttribute("profileDetailDTO", profileDetailDTO);
        return "/memberProfile/detail";
    }

    @PreAuthorize("hasRole('ADMIN') or (hasAuthority('PROFILE_MODIFY') or principal.id==#profileId)")
    @GetMapping("/modify")
    public String getMemberProfileModifyPage(@RequestParam Long profileId, Model model) {
        var profileDetailDTO = memberProfileService.readOneToDetailDTO(profileId);
        if(profileDetailDTO == null) {
            model.addAttribute("msg", "not exist profile");
            return "/error/simple";
        }
        model.addAttribute("profileDetailDTO", profileDetailDTO);
        return "/memberProfile/modify";
    }


}
