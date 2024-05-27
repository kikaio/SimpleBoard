package com.portfolio.simpleboard.repository.reply.search;

import com.portfolio.simpleboard.dto.pager.PageRequestDTO;
import com.portfolio.simpleboard.dto.pager.PageResponseDTO;
import com.portfolio.simpleboard.dto.replies.ReplyDTO;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public interface ReplySearch {

    PageResponseDTO<ReplyDTO> getOnlyReplies(Long postId, PageRequestDTO pageRequestDTO, boolean isDelete);
}
