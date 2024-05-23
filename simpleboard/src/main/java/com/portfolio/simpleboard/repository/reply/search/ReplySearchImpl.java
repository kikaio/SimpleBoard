package com.portfolio.simpleboard.repository.reply.search;

import com.portfolio.simpleboard.dto.pager.PageRequestDTO;
import com.portfolio.simpleboard.dto.pager.PageResponseDTO;
import com.portfolio.simpleboard.dto.replies.ReplyDTO;
import com.portfolio.simpleboard.entity.Reply;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class ReplySearchImpl extends QuerydslRepositorySupport implements ReplySearch {

    public ReplySearchImpl() {
        super(Reply.class);
    }

    @Override
    public PageResponseDTO<ReplyDTO> getOnlyReplies(Long postId, PageRequestDTO pageRequestDTO) {
        return null;
    }
}
