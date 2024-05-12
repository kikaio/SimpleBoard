package com.portfolio.simpleboard.dto;


import lombok.*;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.lang.invoke.MethodHandles;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class PageRequestDTO {

    @Builder.Default
    private int pageNumber = 1;

    @Builder.Default
    private int pageSize = 10;

    //type의 경우 검색 대상에 대합 값으로 알팝벳 1글자로 구성된다.
    private String type;

    private String keyword;

    private String link;

    @Builder.Default
    private boolean isDescending = true;

    public String[] getTypes() {
        if(type == null || type.length() == 0)
            return null;
        return type.split("");
    }

    public Pageable getPageable(String... probs) {
        Pageable pageable = PageRequest.of(pageNumber-1, pageSize, Sort.by(probs).descending());
        return pageable;
    }

    public String getLink() {
        if(link != null || link.length() > 0)
            return link;

        StringBuffer sb = new StringBuffer();
        sb.append("page=%d".formatted(pageNumber));
        sb.append("&size=%d".formatted(pageSize));

        if(type != null && type.length() > 0) {
            sb.append("&type=%s".formatted(type));
        }

        if(keyword != null && keyword.length() > 0) {
            sb.append("&keyword=%s".formatted(keyword));
        }

        link = sb.toString();

        return link;
    }
}
