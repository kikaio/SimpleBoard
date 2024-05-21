package com.portfolio.simpleboard.controller;


import com.portfolio.simpleboard.dto.pager.PageRequestDTO;
import com.portfolio.simpleboard.dto.posts.PostDTO;
import com.portfolio.simpleboard.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.Map;

@Controller
@Log4j2
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/{id}")
    public String getDetailPost(@PathVariable Long id, PageRequestDTO pageRequestDTO, Model model) {
        var postDTO = postService.readOne(id);
        model.addAttribute("postDTO", postDTO);
        model.addAttribute("link", pageRequestDTO.getLink());
        model.addAttribute("boardId", postDTO.getBoardId());
        return "/posts/detail";
    }

    @GetMapping("/insert")
    public String getInsertPage(Long boardId, String link, Model model) {

        model.addAttribute("boardId", boardId);
        model.addAttribute("link", link);
        return "/posts/insert";
    }

    //todo : 게시글 수정 권한이 존재하는지 여부 확인.
    @GetMapping("/modify/{postId}")
    public String getModifyPostPage(@PathVariable Long postId, String link, Long boardId, Long id, Model model) {
        if(id != postId) {
            //todo : error handle
            return "";
        }

        if(false) {
            //todo : 작성자 본인 혹은 관리자인지 여부 확인.
            return "";
        }
        var postDTO = postService.readOne(postId);
        model.addAttribute("postDTO", postDTO);
        model.addAttribute("boardId", postDTO.getBoardId());
        model.addAttribute("link", link);

        return "/posts/modify";
    }

    @PostMapping("")
    public String insertPost(String link, PostDTO postDTO) {
        Long boardId = postDTO.getBoardId();
        postService.insertPost(postDTO);

        return "redirect:/boards/%d/posts?%s".formatted(boardId, link);
    }

//    @PutMapping("/{postId}")
//    public String modifyPost(@PathVariable Long postId, PostDTO postDTO, String link) {
//        if(postId != postDTO.getId()) {
//        //todo : error
//        }
//            postService.modifyPost(postDTO);
//            return "redirect:/posts/%d?%s".formatted(postId, link);
//    }

    @PutMapping("/{postId}")
    public String modifyPost(@PathVariable Long postId, @RequestBody PostDTO data, @RequestBody String link) {
        log.info("data : %s".formatted(data));
        return "";
    }
    @DeleteMapping("/{id}")
    public ModelAndView deletePostInList(@PathVariable Long id, @RequestBody Map<String, Object> paramMap, RedirectAttributes redirectAttributes) {

        Long postId = Long.parseLong(paramMap.get("postId").toString());
        Long boardId = Long.parseLong(paramMap.get("boardId").toString());
        String link = paramMap.get("link").toString();

        ModelAndView modelAndView = new ModelAndView("redirect:/boards/%d/posts?%s".formatted(boardId, link));
        if(id != postId) {
            modelAndView.setStatus(HttpStatus.BAD_REQUEST);
            return modelAndView;
        }

        var postDTO = postService.readOne(postId);
        if(postDTO.getBoardId() != boardId) {
            modelAndView.setStatus(HttpStatus.BAD_REQUEST);
            return modelAndView;
        }

        postService.deletePost(postId);

        modelAndView.setStatus(HttpStatus.SEE_OTHER);
        return modelAndView;
    }

}
