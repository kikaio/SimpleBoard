package com.portfolio.simpleboard.dto;


import com.portfolio.simpleboard.entity.Board;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Setter
@ToString
public class BoardDTO {

    private Long id;
    @NotNull
    @NotBlank
    @Size(min=3, max=20)
    private String title;

    @NotNull
    @NotBlank
    @Size(min=5, max=50)
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
