package com.portfolio.simpleboard.dto.pager;

import lombok.*;

import java.util.List;

@Getter
@ToString
//@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PageResponseDTO<E> {

    private int page;
    private int size;
    private int total;

    private int start;
    private int end;

    private boolean prev;
    private boolean next;

    private List<E> dtoList;

    @Builder
    public PageResponseDTO(PageRequestDTO pageRequestDTO, List<E> dtoList, int total) {

        int pageCnt = 10;

        this.dtoList = dtoList;

        this.page = pageRequestDTO.getPage();
        this.size = pageRequestDTO.getPageSize();
        this.total = total;

        this.end = (int)( this.page/pageCnt + 1) * pageCnt;
        this.start = this.end - pageCnt + 1;
        if(this.end * this.size > this.total) {
            this.end = ( (this.total + this.size-1) / this.size);
        }

        this.prev = this.start > 1;
        this.next = total > this.end * this.size;
    }
}