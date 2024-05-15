package com.portfolio.simpleboard.dto.pager;

import lombok.*;

import java.util.List;

@Getter
@ToString
//@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PageResponseDTO<E> {

    private int page; //현재 page 번호
    private int size; //현재 page 별 보여주는 항목의 갯수
    private int total; //현재 해당 조건에 대한 모든 항목 갯수

    private int start; //pagination의 시작 번호
    private int end; //pagination의 end 번호

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

        this.end = (int)(Math.ceil(this.page / (double)pageCnt)) * pageCnt;
        this.start = this.end - pageCnt + 1;
        int last = (int)Math.ceil(this.total / (double)this.size );
        if(last == 0)
            last = 1;
        this.end = this.end > last ? last :  this.end;

        this.prev = this.start > 1;
        this.next = total > this.end * this.size;
    }
}