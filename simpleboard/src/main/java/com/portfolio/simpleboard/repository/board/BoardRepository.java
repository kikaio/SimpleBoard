package com.portfolio.simpleboard.repository.board;

import com.portfolio.simpleboard.entity.Board;
import com.portfolio.simpleboard.repository.board.search.BoardSearch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long>, BoardSearch {

}
