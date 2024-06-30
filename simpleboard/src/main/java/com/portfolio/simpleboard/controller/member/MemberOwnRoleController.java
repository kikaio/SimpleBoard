package com.portfolio.simpleboard.controller.member;


import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/members/own/roles")
@Log4j2
@Controller
public class MemberOwnRoleController {

    @GetMapping("/list")
    public String getMemberOwnRoleListPage() {
        return "/member/memberOwnRole";
    }
}
