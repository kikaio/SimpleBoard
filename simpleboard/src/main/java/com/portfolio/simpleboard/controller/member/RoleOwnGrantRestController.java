package com.portfolio.simpleboard.controller.member;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Log4j2
@RequestMapping("/members/roles/grants")
public class RoleOwnGrantRestController {

    @GetMapping("")
    public List<Boolean> getRoleOwnGrantList() {
        return null;
    }
}
