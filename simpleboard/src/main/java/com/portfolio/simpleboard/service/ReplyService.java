package com.portfolio.simpleboard.service;

import com.portfolio.simpleboard.dto.pager.PageRequestDTO;
import com.portfolio.simpleboard.dto.pager.PageResponseDTO;
import com.portfolio.simpleboard.dto.replies.ReplyDTO;
import com.portfolio.simpleboard.repository.post.PostRepository;
import com.portfolio.simpleboard.repository.reply.ReplyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class ReplyService {

    private ReplyRepository replyRepository;

    public PageResponseDTO<ReplyDTO> getReplies(Long postId, PageRequestDTO pageRequestDTO) {
        PageResponseDTO<ReplyDTO> pageResponseDTO = replyRepository.getOnlyReplies(postId, pageRequestDTO);
        return null;
    }

    public void insertReply(Long postId, ReplyDTO replyDTO) {
        //todo : insert reply to db
        return ;
    }

    public void deleteReply(ReplyDTO replyDTO) {
        //todo : delete reply from db
        return ;
    }

    public void modifyReply(ReplyDTO replyDTO) {
        //todo : modify reply in db
        return ;
    }

}
