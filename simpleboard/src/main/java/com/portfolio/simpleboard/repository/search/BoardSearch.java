package com.portfolio.simpleboard.repository.search;

import com.portfolio.simpleboard.dto.BoardDTO;
import com.portfolio.simpleboard.dto.pager.PageRequestDTO;
import com.portfolio.simpleboard.dto.pager.PageResponseDTO;

public interface BoardSearch  {
    PageResponseDTO<BoardDTO> boardSearch(PageRequestDTO pageRequestDTO);
}
