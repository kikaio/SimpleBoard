package com.portfolio.simpleboard.dto.pager;


import jakarta.persistence.MappedSuperclass;
import lombok.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
@Builder
public class PageRequestDTO {

    @Builder.Default
    private int page = 1;

    @Builder.Default
    private int pageSize = 10;

    //type의 경우 검색 대상에 대합 값으로 알팝벳 1글자로 구성된다.
    @Builder.Default
    private String type = "";

    @Builder.Default
    private String keyword = "";

    @Builder.Default
    private String link = "";

    public String[] getTypes() {
        if(type == null || type.length() == 0)
            return null;
        return type.split("");
    }

    public Pageable getPageable(String... probs) {
        Pageable pageable = PageRequest.of(page-1, pageSize, Sort.by(probs).descending());
        return pageable;
    }

    public String getLink() {
        if(link != null || link.length() > 0)
            return link;

        StringBuffer sb = new StringBuffer();
        sb.append("page=%d".formatted(page));
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
