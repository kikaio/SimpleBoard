package com.portfolio.simpleboard.service;

import com.portfolio.simpleboard.dto.pager.PageRequestDTO;
import com.portfolio.simpleboard.dto.pager.PageResponseDTO;
import com.portfolio.simpleboard.dto.posts.PostDTO;
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

    @Transactional
    public void insertPost(PostDTO postDTO) {
        long boardId = postDTO.getBoardId();
        var board = boardRepository.findById(boardId).orElseThrow();
        var post = PostDTO.toEntityWithBoard(board, postDTO);
        postRepository.save(post);
        return ;
    }

    public PostDTO readOne(Long postId) {
        var post = postRepository.findById(postId).orElseThrow();
        var postDTO = PostDTO.fromEntity(post);
        return postDTO;
    }

    public void deletePost(Long postId) {
        postRepository.deleteById(postId);
    }
}
