package com.portfolio.simpleboard.dto.replies;

import com.portfolio.simpleboard.entity.Post;
import com.portfolio.simpleboard.entity.Reply;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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

    @NotNull
    private String writer;

    @Builder.Default
    @NotEmpty
    private String content = "";

    @NotNull
    private Long postId;

    private LocalDateTime cDate;

    private LocalDateTime mDate;

    @NotNull
    @Builder.Default
    private Boolean isDeleted = false;

    public static ReplyDTO fromEntity(Reply reply) {
        return ReplyDTO.builder()
                .id(reply.getId())
                .writer(reply.getWriter())
                .content(reply.getContent())
                .cDate(reply.getCDate())
                .mDate(reply.getMDate())
                .postId(reply.getPost().getId())
                .isDeleted(reply.getIsDeleted())
                .build()
        ;
    }

    public static Reply toEntity(Post post, ReplyDTO replyDTO) {
        return Reply.builder()
                .id(replyDTO.getId())
                .writer(replyDTO.getWriter())
                .content(replyDTO.getContent())
                .post(post)
                .isDeleted(replyDTO.getIsDeleted())
                .build()
        ;
    }
}
