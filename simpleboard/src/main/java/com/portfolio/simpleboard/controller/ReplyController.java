package com.portfolio.simpleboard.controller;


import com.portfolio.simpleboard.dto.pager.PageRequestDTO;
import com.portfolio.simpleboard.dto.pager.PageResponseDTO;
import com.portfolio.simpleboard.dto.replies.ReplyDTO;
import com.portfolio.simpleboard.service.ReplyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/replies")
@RequiredArgsConstructor
public class ReplyController {

    private final ReplyService replyService;


    @GetMapping(value = "")
    public PageResponseDTO<ReplyDTO> getReplies(@RequestParam Long postId, @RequestParam PageRequestDTO pageRequestDTO) {
        //todo : search replies using pager
        PageResponseDTO<ReplyDTO> pageResponseDTO = replyService.getRepliesNotDelete(postId, pageRequestDTO);
        return pageResponseDTO;
    }

    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ReplyDTO insertReply(@Valid @RequestBody ReplyDTO replyDTO, BindingResult bindingResult) throws BindException {
        if(bindingResult.hasErrors()) {
            throw new BindException(bindingResult);
        }
        //todo : insert reply to db
        ReplyDTO ret = replyService.insertReply(replyDTO);
        return ret;
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ReplyDTO modifyReply(@PathVariable(required = true) Long id, @Valid @RequestBody ReplyDTO replyDTO,  BindingResult bindingResult) throws BindException {
        //todo : modify reply using id
        if(bindingResult.hasErrors()) {
            throw new BindException(bindingResult);
        }
        if(id != replyDTO.getId()) {
            id = replyDTO.getId();
        }

        ReplyDTO ret = replyService.modifyReply(replyDTO);

        return ret;
    }

    @DeleteMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ReplyDTO deleteReply(@PathVariable(required = true) Long id, @RequestBody ReplyDTO replyDTO) {
        //todo : delete reply and sub replies.
        if(id != replyDTO.getId()){
            id = replyDTO.getId();
        }
        replyService.deleteReply(replyDTO);
        return replyDTO;
    }
}
