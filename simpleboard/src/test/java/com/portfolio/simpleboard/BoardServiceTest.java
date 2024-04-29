package com.portfolio.simpleboard;


import com.portfolio.simpleboard.dto.BoardDTO;
import com.portfolio.simpleboard.repository.BoardRepository;
import com.portfolio.simpleboard.service.BoardService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.Serializable;

@SpringBootTest
public class BoardServiceTest{

    @Autowired
    private BoardService boardService;

    @Test
    @DisplayName("test board entity insert")
    public void insertTest() {
        var boardDTO = BoardDTO.builder()
                .title("test")
                .desc("board for test")
                .build();
        boardService.insert(boardDTO);
        return ;
    }
}
