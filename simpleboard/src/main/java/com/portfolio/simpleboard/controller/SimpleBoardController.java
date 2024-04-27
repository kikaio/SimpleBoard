package com.portfolio.simpleboard.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class SimpleBoardController {

    @GetMapping("")
    public String index() {
        return "index";
    }
}
