package com.portfolio.simpleboard.controller.member;

import com.portfolio.simpleboard.dto.member.MemberOwnRoleSearchDTO;
import com.portfolio.simpleboard.dto.member.MemberProfileDTO;
import com.portfolio.simpleboard.dto.pager.PageRequestDTO;
import com.portfolio.simpleboard.dto.pager.PageResponseDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@Log4j2
@RequestMapping("/members/own/roles")
public class MemberOwnRoleRestController {


    @GetMapping("")
    public MemberOwnRoleSearchDTO searchMemberProfile(PageRequestDTO pageRequestDTO) {

        List<MemberProfileDTO> dtoList = new ArrayList<>();
        MemberProfileDTO memberProfileDTO = MemberProfileDTO.builder()
                .id(1L)
                .nickname("test user")
                .build();
        dtoList.add(memberProfileDTO);

        PageResponseDTO<MemberProfileDTO> pageResponseDTO = PageResponseDTO.<MemberProfileDTO>builder()
                .dtoList(dtoList)
                .total(1)
                .pageRequestDTO(pageRequestDTO)
                .build();

        MemberOwnRoleSearchDTO ret = MemberOwnRoleSearchDTO.builder()
                .pageRequestDTO(pageRequestDTO)
                .pageResponseDTO(pageResponseDTO)
                .build();
        return ret;
    }
}
