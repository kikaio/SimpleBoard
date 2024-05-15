package com.portfolio.simpleboard.controller;

import com.portfolio.simpleboard.dto.BoardDTO;
import com.portfolio.simpleboard.dto.pager.PageRequestDTO;
import com.portfolio.simpleboard.dto.pager.PageResponseDTO;
import com.portfolio.simpleboard.service.BoardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@RequestMapping("/boards")
public class BoardController {

    private final BoardService boardService;

    @GetMapping("")
    public String getBoardListPage(PageRequestDTO pageRequestDTO, Model model) {
        PageResponseDTO<BoardDTO> boards = boardService.getBoards(pageRequestDTO);

        model.addAttribute("pageResponseDTO", boards);
        model.addAttribute("pageRequestDTO", pageRequestDTO);
        return "/boards/list";
    }

    @GetMapping("/insert")
    public String getBoardInsertPage() {

        return "/boards/insert";
    }

    @GetMapping("/detail/{id}")
    public String getBoardDetailPage(@PathVariable(required = true, name = "id") Long id) {
        return "/boards/detail";
    }

    @GetMapping("/modify/{id}")
    public String getBoardModifyPage(@PathVariable(required = true, name = "id") Long id) {
        return "boards/modify";
    }

    @PostMapping(value="")
    public String boardInsert(@Valid BoardDTO boardDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if(bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/boards/insert";
        }
        Long id = boardService.insert(boardDTO);
        redirectAttributes.addFlashAttribute("newBoardId", id);
        return "redirect:/boards";
    }

    @PutMapping("/{id}")
    public String boardUpdate(@PathVariable(name = "id") Long id, BoardDTO boardDTO) {
        if(id != boardDTO.getId()) {
            //return error
        }
        Long newBoardId = boardService.modifyBoard(boardDTO);
        return "redirect:/boards/detail/%d".formatted(id);
    }

    @DeleteMapping("/{id}")
    public String boardDelete(@PathVariable(name = "id")Long id, BoardDTO boardDTO) {
        if(id != boardDTO.getId()){
            //return error

        }
        boardService.deleteBoardById(id);
        return "redirect:/boards";
    }
}
