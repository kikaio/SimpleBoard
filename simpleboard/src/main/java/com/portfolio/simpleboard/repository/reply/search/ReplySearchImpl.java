package com.portfolio.simpleboard.repository.reply.search;

import com.portfolio.simpleboard.dto.pager.PageRequestDTO;
import com.portfolio.simpleboard.dto.pager.PageResponseDTO;
import com.portfolio.simpleboard.dto.replies.ReplyDTO;
import com.portfolio.simpleboard.entity.QReply;
import com.portfolio.simpleboard.entity.Reply;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;
import java.util.stream.Collectors;

public class ReplySearchImpl extends QuerydslRepositorySupport implements ReplySearch {

    public ReplySearchImpl() {
        super(Reply.class);
    }

    @Override
    public PageResponseDTO<ReplyDTO> getOnlyReplies(Long postId, PageRequestDTO pageRequestDTO, boolean isDelete) {

        QReply reply = QReply.reply;
        JPQLQuery<Reply> query = from(reply);

        query.where(reply.post.id.eq(postId));
        query.where(reply.isDeleted.eq(isDelete));
        query.where(reply.id.gt(0L));

        //reply just use page info.
        getQuerydsl().applyPagination(pageRequestDTO.getPageable("id"), query);

        List<Reply> replies = query.fetch();
        int cnt = (int)query.fetchCount();
        List<ReplyDTO> dtos = replies.stream()
                .map(x->{
                    return ReplyDTO.builder()
                            .id(x.getId())
                            .postId(x.getPost().getId())
                            .writer(x.getWriter())
                            .content(x.getContent())
                            .cDate(x.getCDate())
                            .mDate(x.getMDate())
                            .build()
                            ;
                })
                .collect(Collectors.toList())
        ;

        return PageResponseDTO.<ReplyDTO>builder()
                .dtoList(dtos)
                .total(cnt)
                .build();

    }
}
