package com.portfolio.simpleboard;

import com.portfolio.simpleboard.dto.pager.PageRequestDTO;
import com.portfolio.simpleboard.dto.pager.PageResponseDTO;
import com.portfolio.simpleboard.dto.replies.ReplyDTO;
import com.portfolio.simpleboard.service.ReplyService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@Log4j2
@SpringBootTest
public class ReplyServiceTest {

    @Autowired
    private ReplyService replyService;

    @Test
    @Disabled
    @DisplayName("test reply entities get list")
    public void getReplies() {
        long postId = 1;
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .page(1)
                .keyword("")
                .pageSize(10)
                .type("")
                .build()
            ;
        PageResponseDTO<ReplyDTO> replies = replyService.getRepliesNotDelete(postId, pageRequestDTO);
        log.info(replies);
    }

    @Test
    @Disabled
    @DisplayName("test reply modify")
    public void modifyReply() {

        long postId = 1;
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .page(1)
                .pageSize(10)
                .keyword("")
                .type("")
                .build()
                ;

        var pageResponse = replyService.getRepliesNotDelete(postId, pageRequestDTO);

        var firstReply = pageResponse.getDtoList().stream().findFirst();
        if(firstReply.isEmpty() == false) {
            var reply = firstReply.get();
            long replyId = reply.getId();
            var newContent = "this is modify test for %d".formatted(replyId);
            reply.setContent(newContent);
            replyService.modifyReply(reply);
            log.info("try modify reply [%d][%s]".formatted(replyId, newContent));
        }
        return ;
    }

    @Test
    @Disabled
    @DisplayName("insert reply to post")
    public void insertReply() {
        long postId = 10;

        for(int i = 1; i < 10; i++) {
            ReplyDTO newReply = ReplyDTO.builder()
                    .postId(postId)
                    .content("this is sample reply_%d for %d".formatted(i, postId))
                    .writer("tester_%d".formatted(i))
                    .build()
                    ;
            replyService.insertReply(newReply);
            log.info("try insert reply : %s".formatted(newReply.toString()));
        }
        return ;
    }

    @Test
    @Disabled
    @DisplayName("try delete reply")
    public void deleteReplyTest() {
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .page(1)
                .pageSize(10)
                .type("")
                .keyword("")
                .build();
        long postId = 0;

        var pageResponse = replyService.getRepliesNotDelete(postId, pageRequestDTO);
        if(pageResponse.getDtoList().size() > 0) {
            var replyDTO = pageResponse.getDtoList().get(0);
            log.info("try delete reply : %s".formatted(replyDTO));
            replyService.deleteReply(replyDTO);
        }
        return ;
    }
}
