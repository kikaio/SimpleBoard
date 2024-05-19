package com.portfolio.simpleboard.repository.board.delete;

import com.portfolio.simpleboard.entity.Board;
import com.portfolio.simpleboard.entity.QBoard;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.dml.UpdateClause;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAUpdateClause;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class BoardDeleteImpl extends QuerydslRepositorySupport implements BoardDelete{

    public BoardDeleteImpl() {
        super(Board.class);
    }

    @Override
    public void deleteOnlyBoard(Long boardId) {
        QBoard board = QBoard.board;
        UpdateClause<JPAUpdateClause> updateBuilder = update(board);


    }
}
