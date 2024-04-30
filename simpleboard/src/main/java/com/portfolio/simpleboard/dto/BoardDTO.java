package com.portfolio.simpleboard.dto;


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

}
