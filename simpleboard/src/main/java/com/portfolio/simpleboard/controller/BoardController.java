package com.portfolio.simpleboard.controller;

import com.portfolio.simpleboard.dto.BoardDTO;
import com.portfolio.simpleboard.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/boards")
public class BoardController {

    private final BoardService boardService;

    @GetMapping("")
    public String getBoardListPage(Model model) {
        List<BoardDTO> dtoList = boardService.getBoards();
        model.addAttribute("boardDTOList", dtoList);
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

    @PostMapping("")
    public String boardInsert(BoardDTO boardDTO, RedirectAttributes redirectAttributes) {
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
