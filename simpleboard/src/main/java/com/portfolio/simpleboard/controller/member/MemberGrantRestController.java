package com.portfolio.simpleboard.controller.member;


import com.portfolio.simpleboard.dto.member.MemberGrantDTO;
import com.portfolio.simpleboard.service.member.MemberGrantService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/members/grants")
public class MemberGrantRestController {

    private final MemberGrantService memberGrantService;

    @GetMapping("")
    public List<MemberGrantDTO> getMemberGrants() {

        var ret = memberGrantService.getMemberGrants();
        return ret;
    }

    @GetMapping("/{id}")
    public MemberGrantDTO getMemberGrantDTO(Long id) {
        var ret = memberGrantService.getMemberGrant(id);
        return ret;
    }

    @PostMapping("")
    public boolean insertMemberGrantList(@RequestBody List<MemberGrantDTO> memberGrantDTOs) {
        boolean ret = memberGrantService.insertMemberGrantList(memberGrantDTOs);
        return ret;
    }

    @DeleteMapping("/{id}")
    public boolean deleteMemberGrant(@PathVariable Long id, @RequestBody MemberGrantDTO memberGrantDTO) {
        if(id != memberGrantDTO.getId())
            return false;
        boolean ret = memberGrantService.deleteMemberGrant(memberGrantDTO);
        return ret;
    }

    @DeleteMapping("")
    public boolean deleteMemberGrantList(@RequestBody List<MemberGrantDTO> memberGrantDTOs) {
        boolean ret = memberGrantService.deleteMemberGrantList(memberGrantDTOs);
        return ret;
    }

    @PutMapping("")
    public boolean updateMemberGrant(@RequestBody MemberGrantDTO memberGrantDTO) {
        boolean ret = memberGrantService.updateMemberGrant(memberGrantDTO);
        return ret;
    }

}
