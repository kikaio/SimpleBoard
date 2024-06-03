package com.portfolio.simpleboard;

import com.portfolio.simpleboard.dto.posts.PostDTO;
import com.portfolio.simpleboard.entity.Post;
import com.portfolio.simpleboard.entity.PostImage;
import com.portfolio.simpleboard.repository.post.PostRepository;
import com.portfolio.simpleboard.service.PostService;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@Log4j2
@SpringBootTest
public class PostServiceTest {
    @Autowired
    private PostService  postService;
    @Autowired
    private PostRepository postRepository;
    @Test
    @Disabled
    @DisplayName("insert test for image to post")
    public void testInsertPostImage() {
        Post post = Post.builder()
                .title("test for image")
                .content("blablabla")
                .writer("admin")
                .build();

        int testCaseCnt = 3;
        for(int i = 0; i < testCaseCnt; i++) {
            post.addImage(UUID.randomUUID().toString(), "file_%d.jpg".formatted(i));
        }

        postRepository.save(post);
        return ;
    }

    @Test
    @Disabled
    @DisplayName("test for read one post with image set")
    @Transactional
    public void testReadWithImages(){
        long postId = 0;
        var ret = postRepository.findById(postId).orElseThrow();
        log.info(ret);
        log.info("===================");
        for (PostImage postImage : ret.getImageSet()) {
            log.info(postImage);
        }
    }

    @Test
    @Disabled
    @DisplayName("test for imageSet modify")
    public void testModifyImagesSet() {
        Long postId = 0L;
        var post = postRepository.findByIdWithImages(postId).orElseThrow();
        post.clearImages();
        int testCaseCnt = 2;
        for(int i = 0; i < testCaseCnt; i++) {
            post.addImage(UUID.randomUUID().toString(), "updateFile_%d.jpg".formatted(i));
        }

        postRepository.save(post);

        return ;
    }
}
