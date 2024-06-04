package com.portfolio.simpleboard;

import com.portfolio.simpleboard.dto.pager.PageRequestDTO;
import com.portfolio.simpleboard.dto.posts.PostDTO;
import com.portfolio.simpleboard.entity.Post;
import com.portfolio.simpleboard.entity.PostImage;
import com.portfolio.simpleboard.repository.post.PostRepository;
import com.portfolio.simpleboard.repository.reply.ReplyRepository;
import com.portfolio.simpleboard.service.PostService;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import java.util.Arrays;
import java.util.UUID;

@Log4j2
@SpringBootTest
public class PostServiceTest {
    @Autowired
    private PostService  postService;
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ReplyRepository replyRepository;

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

    @Test
    @DisplayName("test for delete post and all replies")
    @Transactional
    @Commit
    public void testREmoveAll() {
        Long postId = 0L;
        replyRepository.deleteByPost_Id(postId);
        postRepository.deleteById(postId);
    }

    @Test
    @DisplayName("test for insert image to post")
    @Disabled
    public void testInsertAll() {
        Long postId = 0L;
        int testCnt = 100;
        int testCnt2 = 3;
        for(int i = 0; i < testCnt; i++) {
            Post post = Post.builder()
                    .title("image test")
                    .content("content for image test")
                    .writer("image tester")
                    .build();

            for(int k = 0; k < testCnt2; k++) {
                if(i % 5 == 0)
                    continue;
                post.addImage(UUID.randomUUID().toString(), "%d file %d.jpg".formatted(i, k));
            }
            postRepository.save(post);
        }
    }

    @Test
    @Transactional
    @DisplayName("test for earch about post and reply and images")
    public void testSearchImageReplyCount() {
        Long boardId = 0L;
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .page(1)
                .type("")
                .pageSize(10)
                .keyword("")
                .build();
        var result = postRepository.searchWithAll(pageRequestDTO, boardId);
        log.info("==========================");
        log.info("totla ele cnt : %d".formatted(result.getTotal()));

        result.getDtoList().forEach(dto->{
            log.info(dto);
        });
        return ;
    }

    @Test
    @DisplayName("test for register with postimages")
    @Disabled
    public void testRegisterWithImages() {
        log.info("testRegisterWithImages");

        PostDTO postDTO = PostDTO.builder()
                .title("post for test with images insert")
                .writer("tester3")
                .content("just content for test")
                .build();

        postDTO.setFileNames(
                Arrays.asList(
                        "%d_aaa.jpg".formatted(UUID.randomUUID())
                        , "%d_bbb.jpg".formatted(UUID.randomUUID())
                        , "%d_ccc.jpg".formatted(UUID.randomUUID())
                )
        );

        Long postId = postService.insertPost(postDTO);
        log.info("%d was inserted".formatted(postId));
        return ;
    }

    @Test
    @DisplayName("test one post read with img")
    @Disabled
    public void testReadOneWithImg() {
        Long postId = 0L;
        PostDTO postDTO = postService.readOne(postId);
        log.info(postDTO);
        postDTO.getFileNames().forEach(x->{
            log.info(x);
        });
        return ;
    }

}
