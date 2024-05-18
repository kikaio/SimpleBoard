package com.portfolio.simpleboard.repository.post.search;

import com.portfolio.simpleboard.dto.pager.PageRequestDTO;
import com.portfolio.simpleboard.dto.pager.PageResponseDTO;
import com.portfolio.simpleboard.dto.posts.PostDTO;
import com.portfolio.simpleboard.entity.Post;
import com.portfolio.simpleboard.entity.QPost;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.stream.Collectors;


public class PostSearchImpl extends QuerydslRepositorySupport implements PostSearch {
    public PostSearchImpl(){
        super(Post.class);
    }

    @Override
    public PageResponseDTO<PostDTO> getOnlyPosts(PageRequestDTO pageRequestDTO, long boardId) {
        QPost post = QPost.post;
        JPQLQuery<Post> query = from(post);

        String[] types = pageRequestDTO.getTypes();
        String keyword = pageRequestDTO.getKeyword();
        Pageable pageable = pageRequestDTO.getPageable();
        if(types != null && types.length > 0 && keyword != null && keyword.length() > 0) {
            BooleanBuilder bb = new BooleanBuilder();
            for(String t : types) {
                switch(t) {
                    case "t":   //board title
                        bb.or(post.title.contains(keyword));
                        break;
                    case "c":   //board description
                        bb.or(post.content.contains(keyword));
                        break;
                    case "w":
                        bb.or(post.writer.contains(keyword));
                        break;
                }
            }
            query.where(bb);
        }
        query.where(post.board.id.eq(boardId));
        query.where(post.id.gt(0));
        getQuerydsl().applyPagination(pageRequestDTO.getPageable(), query);

        var postList = query.fetch();
        int total = (int)query.fetchCount();
        var postDtoList = postList.stream()
                .map(entity->{
                    return PostDTO.fromEntity(entity);
                })
                .collect(Collectors.toList())
        ;

        return PageResponseDTO.<PostDTO>builder()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(postDtoList)
                .total(total)
                .build()
        ;
    }

}

