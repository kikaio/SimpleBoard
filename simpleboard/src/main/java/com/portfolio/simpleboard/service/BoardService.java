package com.portfolio.simpleboard.service;

import com.portfolio.simpleboard.dto.BoardDTO;
import com.portfolio.simpleboard.entity.Board;
import com.portfolio.simpleboard.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public BoardDTO readOne(Long id) {
        var board = boardRepository.findById(id).orElseThrow();

        return toBoardDTO(board);
    }

    public List<BoardDTO> getBoards() {
        var boards = boardRepository.findAll();
        List<BoardDTO> dtoList = boards.stream()
                .map(x->toBoardDTO(x))
                .collect(Collectors.toList());
        return dtoList;
    }

    public Long insert(BoardDTO boardDTO) {
        var newBoard = boardRepository.save(fromBoardDTo(boardDTO));
        return boardDTO.getId();
    }

    public void deleteBoardById(Long id) {
        boardRepository.deleteById(id);
        return ;
    }

    public Long modifyBoard(BoardDTO boardDTO) {
        var board = fromBoardDTo(boardDTO);
        Long id = boardRepository.save(board).getId();
        return id;
    }

    private Board fromBoardDTo(BoardDTO boardDTO) {
        return Board.builder()
                .id(boardDTO.getId())
                .title(boardDTO.getTitle())
                .description(boardDTO.getDescription())
                .build();
    }

    private BoardDTO toBoardDTO(Board board) {
        BoardDTO boardDTO = BoardDTO.builder()
                .id(board.getId())
                .title(board.getTitle())
                .description(board.getDescription())
                .build();
        return boardDTO;
    }
}
