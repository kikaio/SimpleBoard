package com.portfolio.simpleboard.dto.posts;


import com.portfolio.simpleboard.dto.BoardDTO;
import com.portfolio.simpleboard.entity.Board;
import com.portfolio.simpleboard.entity.Post;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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

    private List<String> fileNames;

    static public PostDTO fromEntity(Post post) {
        var dto = PostDTO.builder()
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
        List<String> fileNames = post.getImageSet()
                .stream().sorted()
                .map(img->{
                    return "%s_%s".formatted(img.getUuid(), img.getFileName());
                })
                .collect(Collectors.toList());
        dto.setFileNames(fileNames);
        return dto;
    }

    static public Post toEntityWithBoard(Board board, PostDTO postDTO) {

        var post = Post.builder()
                .id(postDTO.getId())
                .board(board)
                .title(postDTO.getTitle())
                .content(postDTO.getContent())
                .writer(postDTO.getWriter())
                .isDeleted(postDTO.getIsDelete())
                .build()
        ;
        postDTO.fileNames.forEach(name -> {
            String[] strs = name.split("_");
            post.addImage(strs[0], strs[1]);
        });
        return post;
    }
}
