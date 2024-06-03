package com.portfolio.simpleboard.repository.post;


import com.portfolio.simpleboard.dto.pager.PageRequestDTO;
import com.portfolio.simpleboard.dto.pager.PageResponseDTO;
import com.portfolio.simpleboard.dto.posts.PostDTO;
import com.portfolio.simpleboard.entity.Post;
import com.portfolio.simpleboard.repository.post.search.PostSearch;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long>, PostSearch {

    List<Post> findByBoard_Id(Long boardId);

    @EntityGraph(attributePaths = {
            "imageSet"
    }) // 같이 로딩해야하는 속성 명시
    @Query("SELECT p from Post p where p.id=:id")
    Optional<Post> findByIdWithImages(Long id);
}
