package com.portfolio.simpleboard.service;


import com.portfolio.simpleboard.dto.member.MemberSignUpDTO;
import com.portfolio.simpleboard.entity.AccountPlatform;
import com.portfolio.simpleboard.entity.MemberOwnRole;
import com.portfolio.simpleboard.entity.MemberRole;
import com.portfolio.simpleboard.entity.RoleOwnGrant;
import com.portfolio.simpleboard.enums.OAuthPlatform;
import com.portfolio.simpleboard.repository.member.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
public class SimpleBoardUserDetailService implements UserDetailsService {



    private final AccountPlatformRepository accountPlatformRepository;
    private final MemberOwnRoleRepository memberOwnRoleRepository;
    private final MemberOwnGrantRepository memberOwnGrantRepository;
    private final RoleOwnGrantRepository roleOwnGrantRepository;
    private final MemberProfileRepository memberProfileRepository;
    private final MemberRoleRepository memberRoleRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        log.info("login try user name : %s".formatted(email));
        //todo : 유저 별 권한 정보 및 역할 로딩 후 반영
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        AccountPlatform ap = accountPlatformRepository.findByEmailAndPlatformType(email, OAuthPlatform.GUEST).orElse(null);
        if(ap == null) {
            log.error("%s-%s user not exist in account platform.".formatted(email, OAuthPlatform.GUEST));
            throw new UsernameNotFoundException("%s-%s user not exist in account platform.".formatted(email, OAuthPlatform.GUEST));
        }

        var memberProfile = ap.getMemberProfile();
        var grantSet = memberOwnGrantRepository.searchMemberOwnGrantEntities(memberProfile);
        var roleSet = memberOwnRoleRepository.searchMemberOwnRoleEntities(memberProfile);
        var roleOwnGrantSet = roleOwnGrantRepository.searchGrantEntities(roleSet);
        grantSet.addAll(roleOwnGrantSet);

        grantSet.forEach(ele->{
            memberProfile.getAuthorities().add(new SimpleGrantedAuthority(ele.getName()));
        });
        roleSet.forEach(ele->{
            memberProfile.getAuthorities().add(new SimpleGrantedAuthority(ele.getName()));
        });
        memberProfile.setEmail(email);
        memberProfile.setPassword(ap.getPassword());

        return memberProfile;
    }

    @Transactional
    public boolean memberSignup(MemberSignUpDTO memberSignUpDTO, PasswordEncoder passwordEncoder) {
        // change password to encoded password
        String encodedPassword = passwordEncoder.encode(memberSignUpDTO.getPassword());
        memberSignUpDTO.setPassword(encodedPassword);

        var profile = memberSignUpDTO.toMemberProfile();
        var accountPlatform = memberSignUpDTO.toAccountPlatform();

        var otherAccount = accountPlatformRepository.findByEmailAndPlatformType(memberSignUpDTO.getEmail(), memberSignUpDTO.getOAuthPlatform()).orElse(null);
        if(otherAccount != null) {
            log.error("[%s][%s] member already exist!!!".formatted(otherAccount.getEmail(), otherAccount.getPlatformType()));
            return false;
        }

        var otherProfile = memberProfileRepository.findByNickname(memberSignUpDTO.getNickname()).orElse(null);
        if(otherProfile != null) {
            log.error("this nickname[%s] already exist!".formatted(memberSignUpDTO.getNickname()));
            return false;
        }

        profile = memberProfileRepository.save(profile);
        accountPlatform.setMemberProfile(profile);
        accountPlatform = accountPlatformRepository.save(accountPlatform);

        var memberRole = memberRoleRepository.findByName("ROLE_USER").orElse(null);
        if(memberRole == null) {
            memberRole = MemberRole.builder()
                    .name("ROLE_USER")
                    .description("default role for all user")
                    .build();
            memberRole = memberRoleRepository.save(memberRole);

            var adminRole = memberRoleRepository.findByName("ROLE_ADMIN").orElse(null);
            if(adminRole == null) {
                adminRole = MemberRole.builder()
                        .name("ROLE_ADMIN")
                        .description("role for admin")
                        .build();
                adminRole = memberRoleRepository.save(adminRole);
            }
        }

        var memberOwnRoleKey = new MemberOwnRole.MemberOwnRoleId(profile, memberRole);
        var memberOwnRole = memberOwnRoleRepository.findById(memberOwnRoleKey).orElse(null);
        if(memberOwnRole == null) {
            memberOwnRole = MemberOwnRole.builder()
                    .memberOwnRoleId(new MemberOwnRole.MemberOwnRoleId())
                    .build();
            memberOwnRole = memberOwnRoleRepository.save(memberOwnRole);
        }

        return true;
    }

}
