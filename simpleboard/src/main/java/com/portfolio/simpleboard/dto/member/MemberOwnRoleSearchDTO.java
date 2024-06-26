package com.portfolio.simpleboard.dto.member;


import com.portfolio.simpleboard.dto.pager.PageRequestDTO;
import com.portfolio.simpleboard.dto.pager.PageResponseDTO;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access=AccessLevel.PROTECTED)
public class MemberOwnRoleSearchDTO {

    private PageRequestDTO pageRequestDTO;

    private PageResponseDTO<MemberProfileDTO> pageResponseDTO;

    public static MemberOwnRoleSearchDTO fromEntities(PageRequestDTO pageRequestDTO, PageResponseDTO<MemberProfileDTO> pageResponseDTO) {
        return MemberOwnRoleSearchDTO.builder()
                .pageRequestDTO(pageRequestDTO)
                .pageResponseDTO(pageResponseDTO)
                .build();
    }
}
