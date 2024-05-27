package com.portfolio.simpleboard.controller;


import com.portfolio.simpleboard.dto.pager.PageRequestDTO;
import com.portfolio.simpleboard.dto.pager.PageResponseDTO;
import com.portfolio.simpleboard.dto.replies.ReplyDTO;
import com.portfolio.simpleboard.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/replies")
@RequiredArgsConstructor
public class ReplyController {

    private final ReplyService replyService;


    @GetMapping("")
    public ResponseEntity<PageResponseDTO<ReplyDTO>> getReplies(@RequestParam Long postId, @RequestParam PageRequestDTO pageRequestDTO) {
        //todo : search replies using pager
        PageResponseDTO<ReplyDTO> pageResponseDTO = replyService.getRepliesNotDelete(postId, pageRequestDTO);
        return ResponseEntity.ok(null);
    }

    @PostMapping("")
    public ResponseEntity<Boolean> insertReply(ReplyDTO replyDTO) {
        //todo : insert reply to db
        replyService.insertReply(replyDTO);
        return ResponseEntity.ok(true);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Boolean> modifyReply(@PathVariable Long id, ReplyDTO replyDTO) {
        //todo : modify reply using id
        if(id != replyDTO.getId())
        {
            return ResponseEntity.badRequest().body(false);
        }

        replyService.modifyReply(replyDTO);

        return ResponseEntity.ok(true);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteReply(@PathVariable Long id, ReplyDTO replyDTO) {
        //todo : delete reply and sub replies.
        return ResponseEntity.ok(true);
    }
}
