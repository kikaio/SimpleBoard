package com.portfolio.simpleboard.service;

import com.portfolio.simpleboard.dto.BoardDTO;
import com.portfolio.simpleboard.dto.pager.PageRequestDTO;
import com.portfolio.simpleboard.dto.pager.PageResponseDTO;
import com.portfolio.simpleboard.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public BoardDTO readOne(Long id) {
        var board = boardRepository.findById(id).orElseThrow();

        return BoardDTO.fromEntity(board);
    }

    public PageResponseDTO<BoardDTO> getBoards(PageRequestDTO pageRequestDTO) {
        PageResponseDTO<BoardDTO> boardSearch = boardRepository.boardSearch(pageRequestDTO);
        return boardSearch;
    }

    public Long insert(BoardDTO boardDTO) {
        var newBoard = boardRepository.save(BoardDTO.toEntity(boardDTO));
        return boardDTO.getId();
    }

    public void deleteBoardById(Long id) {
        boardRepository.deleteById(id);
        return ;
    }

    public Long modifyBoard(BoardDTO boardDTO) {
        var board = BoardDTO.toEntity(boardDTO);
        Long id = boardRepository.save(board).getId();
        return id;
    }

}
