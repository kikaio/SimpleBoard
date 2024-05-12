package com.portfolio.simpleboard.repository;

import com.portfolio.simpleboard.entity.Board;
import com.portfolio.simpleboard.repository.search.BoardSearch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long>, BoardSearch {

}
