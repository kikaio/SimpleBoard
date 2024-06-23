package com.portfolio.simpleboard.controller.member;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Log4j2
@RequestMapping("/members/roles/grants")
public class RoleOwnGrantController {

    @GetMapping("/list")
    public String getRoleOwnGrantsPage() {
        return "/member/roleOwnGrant";
    }
}
