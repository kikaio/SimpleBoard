package com.portfolio.simpleboard.dto.posts;


import com.portfolio.simpleboard.entity.Post;
import com.portfolio.simpleboard.entity.PostImage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostImageDTO {
    private String uuid;
    private String fileName;
    private int ord;

}
