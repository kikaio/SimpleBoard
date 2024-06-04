package com.portfolio.simpleboard.service;

import com.portfolio.simpleboard.dto.pager.PageRequestDTO;
import com.portfolio.simpleboard.dto.pager.PageResponseDTO;
import com.portfolio.simpleboard.dto.posts.PostDTO;
import com.portfolio.simpleboard.dto.posts.PostListAllDTO;
import com.portfolio.simpleboard.dto.posts.PostWithReplyCntDTO;
import com.portfolio.simpleboard.repository.board.BoardRepository;
import com.portfolio.simpleboard.repository.post.PostRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class PostService {

    private final BoardRepository boardRepository;
    private final PostRepository postRepository;

    public PageResponseDTO<PostDTO> getOnlyPostDTOs(PageRequestDTO pageRequestDTO, long boardId) {
        PageResponseDTO<PostDTO> pageResponseDTO = postRepository.getOnlyPosts(pageRequestDTO, boardId);
        return pageResponseDTO;
    }

    public PageResponseDTO<PostWithReplyCntDTO> getPostsWithReplyCnt(PageRequestDTO pageRequestDTO, long boardId) {
        PageResponseDTO<PostWithReplyCntDTO> res = postRepository.getPostsWithReplyCnt(pageRequestDTO, boardId);
        return res;
    }

    @Transactional
    public Long insertPost(PostDTO postDTO) {
        long boardId = postDTO.getBoardId();
        var board = boardRepository.findById(boardId).orElseThrow();
        var post = PostDTO.toEntityWithBoard(board, postDTO);
        return postRepository.save(post).getId();
    }

    public PostDTO readOne(Long postId) {
        var post = postRepository.findByIdWithImages(postId).orElseThrow();
        var postDTO = PostDTO.fromEntity(post);
        return postDTO;
    }

    @Transactional
    public void deletePost(Long postId) {
        postRepository.deleteById(postId);
        return ;
    }

    @Transactional
    public void modifyPost(PostDTO postDTO) {
        long boardId = postDTO.getBoardId();
        var post = postRepository.findById(postDTO.getId()).orElseThrow();
        if(boardId != post.getBoard().getId()) {
            //todo : error logging
            return ;
        }

        post.updatePost(postDTO.getTitle(), postDTO.getContent());

        postRepository.save(post);
        return ;
    }

    public PageResponseDTO<PostListAllDTO> listWithAll(PageRequestDTO pageRequestDTO) {

        return null;
    }
}
