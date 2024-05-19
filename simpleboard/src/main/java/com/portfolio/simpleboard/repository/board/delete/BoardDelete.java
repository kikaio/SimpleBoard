package com.portfolio.simpleboard.repository.board.delete;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public interface BoardDelete {
    void deleteOnlyBoard(Long boardId);
}
