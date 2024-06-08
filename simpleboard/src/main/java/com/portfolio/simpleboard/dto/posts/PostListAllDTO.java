package com.portfolio.simpleboard.dto.posts;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostListAllDTO {

    private Long id;
    private String title;
    private String writer;
    private LocalDateTime mDate;

    private Long replyCount;

//    private List<PostImageDTO> postImages;

    @Builder.Default
    private PostImageDTO mainImage = null;
}
