package com.portfolio.simpleboard.service;

import com.portfolio.simpleboard.dto.pager.PageRequestDTO;
import com.portfolio.simpleboard.dto.pager.PageResponseDTO;
import com.portfolio.simpleboard.dto.replies.ReplyDTO;
import com.portfolio.simpleboard.repository.post.PostRepository;
import com.portfolio.simpleboard.repository.reply.ReplyRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class ReplyService {

    private ReplyRepository replyRepository;
    private PostRepository postRepository;

    public PageResponseDTO<ReplyDTO> getRepliesNotDelete(Long postId, PageRequestDTO pageRequestDTO) {
        PageResponseDTO<ReplyDTO> pageResponseDTO = replyRepository.getOnlyReplies(postId, pageRequestDTO, false);
        return pageResponseDTO;
    }
    @Transactional
    public void insertReply(ReplyDTO replyDTO) {
        var post = postRepository.findById(replyDTO.getPostId()).orElseThrow();
        var reply = ReplyDTO.toEntity(post, replyDTO);
        replyRepository.save(reply);
        return ;
    }

    public void deleteReply(ReplyDTO replyDTO) {
        //todo : delete reply from db
        replyRepository.deleteById(replyDTO.getId());
        return ;
    }

    public void modifyReply(ReplyDTO replyDTO) {
        //todo : modify reply in db

        var reply = replyRepository.findById(replyDTO.getId()).orElseThrow();
        reply.modify(replyDTO.getContent());
        replyRepository.save(reply);
        return ;
    }

}
