package com.portfolio.simpleboard.controller;


import com.portfolio.simpleboard.dto.BoardDTO;
import com.portfolio.simpleboard.dto.pager.PageRequestDTO;
import com.portfolio.simpleboard.dto.posts.PostDTO;
import com.portfolio.simpleboard.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
    public String insertPost(String link, @Valid PostDTO postDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if(bindingResult.hasErrors()) {
            log.error("insertPost has binding errors");
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/posts/insert?link=%s&boardId=%d".formatted(link, postDTO.getBoardId());
        }
        Long boardId = postDTO.getBoardId();
        long newPostId = postService.insertPost(postDTO);
        log.info("%d post inserted!".formatted(newPostId));
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
    public ModelAndView modifyPost(@PathVariable Long postId, @RequestBody Map<String, Object> data) {
        log.info("data : %s".formatted(data));
        String link = data.get("link").toString();
        Long id = Long.parseLong(data.get("id").toString());
        Long boardId = Long.parseLong(data.get("boardId").toString());
        String title = data.get("title").toString();
        String writer = data.get("writer").toString();
        String content = data.get("content").toString();
        var fileNames = (List<String>)data.get("fileNames");

        var postDTO = PostDTO.builder()
                .id(id)
                .boardId(boardId)
                .title(title)
                .writer(writer)
                .content(content)
                .fileNames(fileNames)
                .build()
        ;
        postService.modifyPost(postDTO);
        var modelAndView = new ModelAndView("redirect:/posts/modify/%d?link=%s&boardId=%d&id=%d".formatted(id, link, boardId, postId));
        modelAndView.setStatus(HttpStatus.SEE_OTHER);
        return modelAndView;
    }

    @ResponseStatus(HttpStatus.SEE_OTHER)
    @DeleteMapping("/{id}")
    public String deletePostInList(@PathVariable Long id, @RequestBody Map<String, Object> paramMap, RedirectAttributes redirectAttributes) {

        Long postId = Long.parseLong(paramMap.get("postId").toString());
        Long boardId = Long.parseLong(paramMap.get("boardId").toString());
        String link = paramMap.get("link").toString();

        if(id != postId) {
            redirectAttributes.addFlashAttribute("error", "bad request");
            return "redirect:/boards/%d/posts?%s".formatted(boardId, link);
        }

        var postDTO = postService.readOne(postId);
        if(postDTO.getBoardId() != boardId) {
            redirectAttributes.addFlashAttribute("error", "bad request");
            return "redirect:/boards/%d/posts?%s".formatted(boardId, link);
        }

        postService.deletePost(postId);
        return "redirect:/boards/%d/posts?%s".formatted(boardId, link);
    }

}
