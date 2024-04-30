package com.portfolio.simpleboard;


import com.portfolio.simpleboard.dto.BoardDTO;
import com.portfolio.simpleboard.repository.BoardRepository;
import com.portfolio.simpleboard.service.BoardService;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.Serializable;

@Log4j2
@SpringBootTest
public class BoardServiceTest{

    @Autowired
    private BoardService boardService;

    @Test
    @Disabled
    @DisplayName("test board entity insert")
    public void insertTest() {
        for(int i = 0; i < 10; i++) {
            var boardDTO = BoardDTO.builder()
                    .title("test-%d".formatted(i+1))
                    .description("board for test-%d".formatted(i+1))
                    .build();
            boardService.insert(boardDTO);
        }
        return ;
    }

    @Test
    @Disabled
    @DisplayName("select all boards")
    public void getBoards()
    {
        var dtoList = boardService.getBoards();
        dtoList.forEach(dto->{
            log.info("%s".formatted(dto));
        });

    }

    @Test
    @Disabled
    @DisplayName("delete target Board")
    public void deleteBoardTest() {
        var dtoList = boardService.getBoards();
        if(dtoList.size() > 0) {
            Long targetId = dtoList.get(0).getId();
            boardService.deleteBoardById(targetId);
            log.info("target board deleted. this board id is %d".formatted(targetId));
        }
        else {
            log.info("any board not exist to delete");
        }
    }

    @Test
//    @Disabled
    @DisplayName("modify board test")
    public void modifyBaordTest(){
        var dtoList = boardService.getBoards();
        if(dtoList.size() > 0) {
            var dtoTarget = dtoList.get(0);
            dtoTarget.change(dtoTarget.getTitle(), "changed desc that board id[%d]".formatted(dtoTarget.getId()));
            Long id = boardService.modifyBoard(dtoTarget);
            log.info("board[%d] was changed.".formatted(id));

        } else {
            log.info("any board not exist for modify test");
        }
    }
}
