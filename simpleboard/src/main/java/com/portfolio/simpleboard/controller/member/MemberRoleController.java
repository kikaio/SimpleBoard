package com.portfolio.simpleboard.controller.member;


import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/members/roles")
@Log4j2
public class MemberRoleController {

    @GetMapping("/list")
    public String getRoleListPage() {
        return "/members/roles";
    }
}
