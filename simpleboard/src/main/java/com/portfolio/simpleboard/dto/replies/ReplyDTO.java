package com.portfolio.simpleboard.dto.replies;

import com.portfolio.simpleboard.entity.Post;
import com.portfolio.simpleboard.entity.Reply;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class ReplyDTO {

    private Long id;

    private String writer;

    @Builder.Default
    private String content = "";

    private LocalDateTime cDate;

    private LocalDateTime mDate;

    private Long postId;

    public ReplyDTO fromEntity(Reply reply) {
        return ReplyDTO.builder()
                .id(reply.getId())
                .writer(reply.getWriter())
                .content(reply.getContent())
                .cDate(reply.getCDate())
                .mDate(reply.getMDate())
                .postId(reply.getPost().getId())
                .build()
        ;
    }

    public Reply toEntity(Post post, ReplyDTO replyDTO) {
        return Reply.builder()
                .id(replyDTO.getId())
                .writer(replyDTO.getWriter())
                .content(replyDTO.getContent())
                .post(post)
                .build()
        ;
    }
}
