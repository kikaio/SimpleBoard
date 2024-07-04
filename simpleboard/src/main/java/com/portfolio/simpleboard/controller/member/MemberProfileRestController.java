package com.portfolio.simpleboard.controller.member;

import com.portfolio.simpleboard.dto.member.MemberProfileDTO;
import com.portfolio.simpleboard.dto.member.MemberProfileDetailDTO;
import com.portfolio.simpleboard.dto.member.MemberProfileFlagDTO;
import com.portfolio.simpleboard.dto.pager.PageRequestDTO;
import com.portfolio.simpleboard.dto.pager.PageResponseDTO;
import com.portfolio.simpleboard.service.member.MemberProfileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;


@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/members/profiles")
public class MemberProfileRestController {

    private final MemberProfileService memberProfileService;


    @GetMapping("")
    public PageResponseDTO<MemberProfileDTO> searchMemberProfileList(PageRequestDTO pageRequestDTO) {
        return memberProfileService.searchMemberProfileList(pageRequestDTO);
    }

    @GetMapping("/nickname/check")
    public boolean checkNicknameDuplication(Long id, String nickname) {
        //중복 시 true 반환
        return memberProfileService.checkNicknameDuplication(id, nickname);
    }

    @PutMapping("/{id}")
    public boolean modifyMemberProfile(@PathVariable Long id, @RequestBody MemberProfileDetailDTO dto) {
        if(id != dto.getId()) {
            log.error("invalid member profile id, path variable[%d], dto[%d]".formatted(id, dto.getId()));
            return false;
        }
        //현재 변경되는 정보는 nickname이 유일.
        return memberProfileService.modifyMemberProfileDetail(dto);
    }

    @DeleteMapping("/{id}")
    public boolean deleteMemberProfile(@PathVariable Long id, @RequestBody MemberProfileDetailDTO dto) {
        if(id != dto.getId()) {
            log.error("invalid member profile id, path variable[%d], dto[%d]".formatted(id, dto.getId()));
            return false;
        }

        //isDel flag를 변경한다.
        if(memberProfileService.modifyMemberProfileIsDel(id, dto.isDel()) == false) {
            log.error("member profile delete failed");
            return false;
        }
        return true;
    }

    @PatchMapping("/{id}")
    public boolean modifyMemberProfileFlags(@PathVariable Long id, @RequestBody MemberProfileFlagDTO dto) {
        if (id != dto.getId()) {
            log.error("invalid member profile id, path variable[%d], dto[%d]".formatted(id, dto.getId()));
            return false;
        }

        //flag 변경은 한번에 한개의 요청으로 제한한다.
        var flagMap = dto.getPatchFlagMap();

        boolean ret = false;

        for (var entry : flagMap.entrySet()) {
            var key = entry.getKey();
            var val = entry.getValue();

            switch (key) {
                case "isCredentialsNonExpired": {
                    if (val != null) {
                        ret = memberProfileService.modifyMemberProfileIsNonExpired(id, val);
                    } else {
                        log.error("%s vlaue must be not null".formatted(key));
                    }
                    break;
                }
                case "isAccountNonExpired": {
                    if (val != null) {
                        ret = memberProfileService.modifyMemberProfileIsNonExpired(id, val);
                    } else {
                        log.error("%s vlaue must be not null".formatted(key));
                    }
                    break;
                }
                case "isAccountNonLocked": {
                    if (val != null) {
                        ret = memberProfileService.modifyMemberProfileIsNonLocked(id, val);
                    } else {
                        log.error("%s vlaue must be not null".formatted(key));
                    }
                    break;
                }
                case "isEnabled": {
                    if (val != null) {
                        ret = memberProfileService.modifyMemberProfileIsEnabled(id, val);
                    } else {
                        log.error("%s vlaue must be not null".formatted(key));
                    }
                    break;
                }
                default:
                    log.error("something invalid key.... %s".formatted(key));
                    break;
            }
            if(ret == false)
                break;
        }
        return ret;
    }
}
