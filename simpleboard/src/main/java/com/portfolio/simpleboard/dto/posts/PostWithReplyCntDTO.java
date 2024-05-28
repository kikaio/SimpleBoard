package com.portfolio.simpleboard.dto.posts;

import com.portfolio.simpleboard.entity.Board;
import com.portfolio.simpleboard.entity.Post;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@ToString
public class PostWithReplyCntDTO {

    private Long id;

    private Long boardId;
    @Builder.Default
    private String title = "";

    @Builder.Default
    private String writer = "";

    private LocalDateTime cDate;

    private Long replyCount;

    @NotNull
    @Builder.Default
    private Boolean isDeleted = false;

    static public PostWithReplyCntDTO fromEntity(Post post, Long replyCount) {
        return PostWithReplyCntDTO.builder()
                .id(post.getId())
                .boardId(post.getBoard().getId())
                .title(post.getTitle())
                .writer(post.getWriter())
                .cDate(post.getCDate())
                .replyCount(replyCount)
                .isDeleted(post.getIsDeleted())
                .build()
                ;
    }

    static public Post toEntityWithBoard(Board board, PostWithReplyCntDTO postDTO) {

        return Post.builder()
                .id(postDTO.getId())
                .board(board)
                .title(postDTO.getTitle())
                .writer(postDTO.getWriter())
                .isDeleted(postDTO.getIsDeleted())
                .build()
                ;
    }

}
