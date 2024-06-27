package com.portfolio.simpleboard.controller.member;


import com.portfolio.simpleboard.dto.member.MemberGrantDTO;
import com.portfolio.simpleboard.dto.member.MemberOwnGrantDetailDTO;
import com.portfolio.simpleboard.service.member.MemberOwnGrantService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

@RestController
@Log4j2
@RequestMapping("/members/own/grants")
@RequiredArgsConstructor
public class MemberOwnGrantRestController {

    private final MemberOwnGrantService memberOwnGrantService;

    @GetMapping("")
    public MemberOwnGrantDetailDTO searchMemberOwnGrantDetailDTO(@RequestParam Long profileId)
    {
        return memberOwnGrantService.searchMemberOwnGrantDetailDTO(profileId);
    }

    @PostMapping("{profileId}")
    public boolean createMemberOwnGrant(@PathVariable Long profileId, @RequestBody MemberGrantDTO memberGrantDTO) {
        return memberOwnGrantService.createMemberOwnGrant(profileId, memberGrantDTO);
    }

    @DeleteMapping("{profileId}")
    public boolean deleteMemberOwnGrant(@PathVariable Long profileId, @RequestBody MemberGrantDTO memberGrantDTO) {
        return memberOwnGrantService.deleteMemberOwnGrant(profileId, memberGrantDTO);
    }
}
