package com.portfolio.simpleboard.repository.post.search;

import com.portfolio.simpleboard.dto.pager.PageRequestDTO;
import com.portfolio.simpleboard.dto.pager.PageResponseDTO;
import com.portfolio.simpleboard.dto.posts.PostDTO;
import com.portfolio.simpleboard.dto.posts.PostListAllDTO;
import com.portfolio.simpleboard.dto.posts.PostWithReplyCntDTO;

public interface PostSearch {
    PageResponseDTO<PostDTO> getOnlyPosts(PageRequestDTO pageRequestDTO, long boardId);

    PageResponseDTO<PostWithReplyCntDTO> getPostsWithReplyCnt(PageRequestDTO pageRequestDTO, long boardId);


    PageResponseDTO<PostListAllDTO> searchWithAll(PageRequestDTO pageRequestDTO, Long boardId);

}
