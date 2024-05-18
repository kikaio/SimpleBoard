package com.portfolio.simpleboard.repository.post.search;

import com.portfolio.simpleboard.dto.pager.PageRequestDTO;
import com.portfolio.simpleboard.dto.pager.PageResponseDTO;
import com.portfolio.simpleboard.dto.posts.PostDTO;

public interface PostSearch {
    PageResponseDTO<PostDTO> getOnlyPosts(PageRequestDTO pageRequestDTO, long boardId);
}
