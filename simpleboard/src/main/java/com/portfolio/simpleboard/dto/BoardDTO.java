package com.portfolio.simpleboard.dto;


import com.portfolio.simpleboard.entity.Board;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@ToString
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class BoardDTO {

    private Long id;
    private String title;
    private String description;

    public void change(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public static BoardDTO fromEntity(Board board) {
        return BoardDTO.builder()
                .id(board.getId())
                .title(board.getTitle())
                .description(board.getDescription())
                .build();
    }

    public static Board toEntity(BoardDTO boardDTO) {
        return Board.builder()
                .id(boardDTO.getId())
                .title(boardDTO.getTitle())
                .description(boardDTO.getDescription())
                .build();
    }
}
