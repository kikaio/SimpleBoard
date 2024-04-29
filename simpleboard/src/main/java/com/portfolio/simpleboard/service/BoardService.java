package com.portfolio.simpleboard.service;

import com.portfolio.simpleboard.dto.BoardDTO;
import com.portfolio.simpleboard.entity.Board;
import com.portfolio.simpleboard.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public BoardDTO readOne(Long id) {
        var board = boardRepository.findById(id).orElseThrow();

        return toBoardDTO(board);
    }

    public void insert(BoardDTO boardDTO) {
        boardRepository.save(fromBoardDTo(boardDTO));
    }

    private Board fromBoardDTo(BoardDTO boardDTO) {
        return Board.builder()
                .id(boardDTO.getId())
                .title(boardDTO.getTitle())
                .description(boardDTO.getDesc())
                .build();
    }

    private BoardDTO toBoardDTO(Board board) {
        BoardDTO boardDTO = BoardDTO.builder()
                .id(board.getId())
                .title(board.getTitle())
                .desc(board.getDescription())
                .build();
        return boardDTO;
    }
}
