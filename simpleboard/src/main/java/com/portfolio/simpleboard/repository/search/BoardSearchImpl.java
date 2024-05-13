package com.portfolio.simpleboard.repository.search;

import com.portfolio.simpleboard.dto.BoardDTO;
import com.portfolio.simpleboard.dto.pager.PageRequestDTO;
import com.portfolio.simpleboard.dto.pager.PageResponseDTO;
import com.portfolio.simpleboard.entity.Board;
import com.portfolio.simpleboard.entity.QBoard;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;
import java.util.stream.Collectors;


public class BoardSearchImpl extends QuerydslRepositorySupport implements BoardSearch{

    public BoardSearchImpl() {
        super(Board.class);
    }
    @Override
    public PageResponseDTO<BoardDTO> boardSearch(PageRequestDTO pageRequestDTO) {
        QBoard board = QBoard.board;
        JPQLQuery<Board> query = from(board);
        String[] types = pageRequestDTO.getTypes();
        String keyword = pageRequestDTO.getKeyword();
        Pageable pageable = pageRequestDTO.getPageable();

        if(types != null && types.length > 0 && keyword != null && keyword.length() > 0) {
            BooleanBuilder bb = new BooleanBuilder();
            for(String t : types) {
                switch(t) {
                    case "t":   //board title
                        bb.or(board.title.contains(keyword));
                        break;
                    case "d":   //board description
                        bb.or(board.description.contains(keyword));
                        break;
                }
            }
            query.where(bb);
        }
        query.where(board.id.gt(0));
        this.getQuerydsl().applyPagination(pageable, query);

        List<Board> boardList = query.fetch();
        int cnt = (int)query.fetchCount();

        var boardDtoList = boardList.stream().map(x->{
            return BoardDTO.fromEntity(x);
        }).collect(Collectors.toList());

        return PageResponseDTO.<BoardDTO>builder()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(boardDtoList)
                .total(cnt)
                .build();
    }
}
