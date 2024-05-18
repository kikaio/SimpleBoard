package com.portfolio.simpleboard.controller;


import com.portfolio.simpleboard.dto.pager.PageRequestDTO;
import com.portfolio.simpleboard.dto.posts.PostDTO;
import com.portfolio.simpleboard.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Log4j2
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/insert")
    public String getInsertPage(Long boardId, String link, Model model) {

        model.addAttribute("boardId", boardId);
        model.addAttribute("link", link);
        return "/posts/insert";
    }

    @PostMapping("")
    public String insertPost(String link, PostDTO postDTO) {
        Long boardId = postDTO.getBoardId();
        postService.insertPost(postDTO);

        return "redirect:/boards/%d/posts?%s".formatted(boardId, link);
    }
}
