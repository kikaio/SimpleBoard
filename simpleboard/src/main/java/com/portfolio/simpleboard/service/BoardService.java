package com.portfolio.simpleboard.service;

import com.portfolio.simpleboard.dto.BoardDTO;
import com.portfolio.simpleboard.dto.pager.PageRequestDTO;
import com.portfolio.simpleboard.dto.pager.PageResponseDTO;
import com.portfolio.simpleboard.entity.Reply;
import com.portfolio.simpleboard.repository.board.BoardRepository;
import com.portfolio.simpleboard.repository.post.PostRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final PostRepository postRepository;
//    private final ReplyRepository replyRepository;


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

    @Transactional
    public void deleteBoardById(Long id) {
//        boardRepository.deleteById(id);
        var board = boardRepository.findById(id).orElseThrow();
        board.doDelete();
        boardRepository.save(board);

        //post
        var postList = postRepository.findByBoard_Id(id);
        postList.forEach(x->{
            x.doDelete();
        });
        postRepository.saveAll(postList);

//        //reply
//        ArrayList<Reply> replieList = new ArrayList<>();
//        postList.forEach(x->{
//            var replies = replyRepository.findByPost_Id(x.getId());
//            replieList.addAll(replies);
//        });
//        replieList.forEach(x->{
//            x.doDelete();
//        });
//        replyRepository.saveAll(replieList);

        return ;
    }

    public Long modifyBoard(BoardDTO boardDTO) {
        var board = BoardDTO.toEntity(boardDTO);
        Long id = boardRepository.save(board).getId();
        return id;
    }

}
