package com.portfolio.simpleboard.repository.post.search;

import com.portfolio.simpleboard.dto.pager.PageRequestDTO;
import com.portfolio.simpleboard.dto.pager.PageResponseDTO;
import com.portfolio.simpleboard.dto.posts.PostDTO;
import com.portfolio.simpleboard.dto.posts.PostImageDTO;
import com.portfolio.simpleboard.dto.posts.PostListAllDTO;
import com.portfolio.simpleboard.dto.posts.PostWithReplyCntDTO;
import com.portfolio.simpleboard.entity.Post;
import com.portfolio.simpleboard.entity.PostImage;
import com.portfolio.simpleboard.entity.QPost;
import com.portfolio.simpleboard.entity.QReply;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.ArrayList;
import java.util.List;
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
    public PageResponseDTO<PostWithReplyCntDTO> getPostsWithReplyCnt(PageRequestDTO pageRequestDTO, long boardId){
        QPost post = QPost.post;
        QReply reply = QReply.reply;

        JPQLQuery jpqlQuery = from(post);

        getQuerydsl().applyPagination(pageRequestDTO.getPageable(), jpqlQuery);

/*
    select P.*, COUNT(P) from Post P Inner JOIN Reply AS R ON P.id = R.post_id
    GROUP BY R.postId
*/
        jpqlQuery.leftJoin(reply).on(reply.post.eq(post));
        jpqlQuery.groupBy(post);

        String types = pageRequestDTO.getType();
        if(types != null && types.length() > 0) {

            String[] type = types.split("");
            BooleanBuilder bb = new BooleanBuilder();
            for(String val : type) {
                switch(val) {
                    case "t":
                        bb.or(post.title.contains(val));
                        break;
                    case "c":
                        bb.or(post.content.contains(val));
                        break;
                    case "w":
                        bb.or(post.writer.contains(val));
                        break;
                }
            }
            jpqlQuery.where(bb);
        }
        jpqlQuery.where(post.id.gt(0L));
        jpqlQuery.where(post.isDeleted.eq(false));

        {
            BooleanBuilder bb = new BooleanBuilder();
            bb.or(reply.isDeleted.eq(false));
            bb.or(reply.isDeleted.isNull());
            jpqlQuery.where(bb);
        }

        JPQLQuery<PostWithReplyCntDTO> dtoQuery = jpqlQuery.select(
                Projections.bean(PostWithReplyCntDTO.class
                        , post.id
                        , post.title
                        , post.writer
                        , post.cDate
                        , reply.count().as("replyCount")
                )
        );
        getQuerydsl().applyPagination(pageRequestDTO.getPageable(), dtoQuery);
        var dtoList = dtoQuery.fetch();
        int total = (int)dtoQuery.fetchCount();


        return PageResponseDTO.<PostWithReplyCntDTO>builder()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(dtoList)
                .total(total)
                .build();
    }

    public PageResponseDTO<PostListAllDTO> searchWithAll(PageRequestDTO pageRequestDTO, Long boardId) {

        QPost post = QPost.post;
        QReply reply = QReply.reply;
        JPQLQuery<Post> postQuery = from(post);

        postQuery.leftJoin(reply).on(reply.post.eq(post));

        postQuery.where(post.board.id.eq(boardId));
        {
            BooleanBuilder bb=  new BooleanBuilder();
            bb.or(reply.isDeleted.isNull());
            bb.or(reply.isDeleted.isFalse());
            bb.or(post.isDeleted.isFalse());
            postQuery.where(bb);
        }

        if(pageRequestDTO.getType() != null && pageRequestDTO.getType().length() > 0) {
            BooleanBuilder bb = new BooleanBuilder();
            String keyword = pageRequestDTO.getKeyword();
            if(keyword != null) {
                for(String type : pageRequestDTO.getTypes()) {
                    switch(type) {
                        case "t":
                            bb.or(post.title.contains(keyword));
                            break;
                        case "w":
                            bb.or(post.writer.contains(keyword));
                            break;
                        case "c":
                            bb.or(post.content.contains(keyword));
                            break;
                    }
                }
                postQuery.where(bb);
            }
        }
        postQuery.where(post.id.gt(0L));
        postQuery.groupBy(post);


        getQuerydsl().applyPagination(pageRequestDTO.getPageable(), postQuery);

        JPQLQuery<Tuple> tupleJpql = postQuery.select(post, reply.countDistinct());
        List<Tuple> tupleList = tupleJpql.fetch();

        List<PostListAllDTO> dtoList = tupleList.stream().map(ele -> {
            Post postRet = (Post)ele.get(post);
            long replyCnt = ele.get(1, Long.class);
            PostListAllDTO dto = PostListAllDTO.builder()
                    .id(postRet.getId())
                    .title(postRet.getTitle())
                    .writer(postRet.getWriter())
                    .mDate(postRet.getMDate())
                    .replyCount(replyCnt)
                    .build();

            List<PostImageDTO> imagesDTO = postRet.getImageSet().stream()
                    .sorted()
                    .map(ele2->{
                        return PostImageDTO.builder()
                                .uuid(ele2.getUuid())
                                .fileName(ele2.getFileName())
                                .ord(ele2.getOrd())
                                .build();
                    })
                    .collect(Collectors.toList())
                    ;
            dto.setPostImages(imagesDTO);
            return dto;
        }).collect(Collectors.toList());

        int total = (int)postQuery.fetchCount();


        return PageResponseDTO.<PostListAllDTO>builder()
                .dtoList(dtoList)
                .total(total)
                .pageRequestDTO(pageRequestDTO)
                .build()
                ;

    }
}

