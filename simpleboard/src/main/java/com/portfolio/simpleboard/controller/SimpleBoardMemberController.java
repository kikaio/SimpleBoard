package com.portfolio.simpleboard.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Log4j2
@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class SimpleBoardMemberController {

    @GetMapping("/login")
    public String getLoginPage(String error, String logout) {
        log.info("getLoginPage called");
        return "/member/login";
    }


}
