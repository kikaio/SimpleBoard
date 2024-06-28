package com.portfolio.simpleboard.controller;


import com.portfolio.simpleboard.dto.member.MemberSignUpDTO;
import com.portfolio.simpleboard.repository.member.MemberProfileRepository;
import com.portfolio.simpleboard.service.SimpleBoardUserDetailService;
import com.portfolio.simpleboard.service.member.MemberProfileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Log4j2
@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class SimpleBoardMemberController {

    private final SimpleBoardUserDetailService simpleBoardUserDetailService;

    @GetMapping("/login")
    public String getLoginPage(String error, String logout) {
        log.info("getLoginPage called");

        if(logout != null) {
            log.info("logout!");
        }
        return "/member/login";
    }

    @GetMapping("/signup")
    public String getSignUpPage(String err) {
        log.info("getSignUpPage");
        return "/member/signup";
    }

    @PostMapping("/signup")
    public String createMember(MemberSignUpDTO memberSignUpDTO) {
        boolean ret = simpleBoardUserDetailService.memberSignup(memberSignUpDTO);
        if(ret) {
            log.info("new Member created");
            return "redirect:/login";
        } else {
            return "redirect:/login?error=%s".formatted("sign up failed");
        }
    }
}
