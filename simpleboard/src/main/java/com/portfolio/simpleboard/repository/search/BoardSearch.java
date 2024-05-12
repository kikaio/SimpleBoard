package com.portfolio.simpleboard.repository.search;

import com.portfolio.simpleboard.dto.BoardDTO;
import com.portfolio.simpleboard.dto.PageRequestDTO;
import com.portfolio.simpleboard.dto.PageResponseDTO;
import org.springframework.data.domain.Page;

public interface BoardSearch  {
    PageResponseDTO<BoardDTO> boardSearch(PageRequestDTO pageRequestDTO);
}
