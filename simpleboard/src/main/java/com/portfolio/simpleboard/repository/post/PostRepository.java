package com.portfolio.simpleboard.repository.post;


import com.portfolio.simpleboard.dto.pager.PageRequestDTO;
import com.portfolio.simpleboard.dto.pager.PageResponseDTO;
import com.portfolio.simpleboard.dto.posts.PostDTO;
import com.portfolio.simpleboard.entity.Post;
import com.portfolio.simpleboard.repository.post.search.PostSearch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long>, PostSearch {

    List<Post> findByBoard_Id(Long boardId);
}
