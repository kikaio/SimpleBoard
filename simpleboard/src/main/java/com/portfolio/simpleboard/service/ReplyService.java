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

    private final ReplyRepository replyRepository;
    private final PostRepository postRepository;

    public PageResponseDTO<ReplyDTO> getRepliesNotDelete(Long postId, PageRequestDTO pageRequestDTO) {
        PageResponseDTO<ReplyDTO> pageResponseDTO = replyRepository.getOnlyReplies(postId, pageRequestDTO, false);
        return pageResponseDTO;
    }
    @Transactional
    public ReplyDTO insertReply(ReplyDTO replyDTO) {
        var post = postRepository.findById(replyDTO.getPostId()).orElseThrow();
        var reply = ReplyDTO.toEntity(post, replyDTO);
        reply = replyRepository.save(reply);
        return ReplyDTO.fromEntity(reply);
    }

    public void deleteReply(ReplyDTO replyDTO) {
        //todo : delete reply from db
        replyRepository.deleteById(replyDTO.getId());
        return ;
    }

    public ReplyDTO modifyReply(ReplyDTO replyDTO) {
        //todo : modify reply in db

        var reply = replyRepository.findById(replyDTO.getId()).orElseThrow();
        reply.modify(replyDTO.getContent());
        reply = replyRepository.save(reply);
        return ReplyDTO.fromEntity(reply);
    }

}
