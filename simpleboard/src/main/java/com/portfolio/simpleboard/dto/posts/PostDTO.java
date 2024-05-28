package com.portfolio.simpleboard.dto.posts;


import com.portfolio.simpleboard.dto.BoardDTO;
import com.portfolio.simpleboard.entity.Board;
import com.portfolio.simpleboard.entity.Post;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class PostDTO {

    private Long id;

    private Long boardId;
    @Builder.Default
    private String title = "";

    @Builder.Default
    private String content = "";

    @Builder.Default
    private String writer = "";

    private LocalDateTime cDate;

    private LocalDateTime mDate;

    @NotNull
    @Builder.Default
    private Boolean isDelete = false;

    static public PostDTO fromEntity(Post post) {
        return PostDTO.builder()
                .id(post.getId())
                .boardId(post.getBoard().getId())
                .title(post.getTitle())
                .writer(post.getWriter())
                .content(post.getContent())
                .cDate(post.getCDate())
                .mDate(post.getMDate())
                .isDelete(post.getIsDeleted())
                .build()
        ;
    }

    static public Post toEntityWithBoard(Board board, PostDTO postDTO) {

        return Post.builder()
                .id(postDTO.getId())
                .board(board)
                .title(postDTO.getTitle())
                .content(postDTO.getContent())
                .writer(postDTO.getWriter())
                .isDeleted(postDTO.getIsDelete())
                .build()
        ;
    }
}
