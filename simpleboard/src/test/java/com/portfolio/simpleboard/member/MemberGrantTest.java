package com.portfolio.simpleboard.member;


import com.portfolio.simpleboard.entity.MemberGrant;
import com.portfolio.simpleboard.repository.member.MemberGrantRepository;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Log4j2
@SpringBootTest
public class MemberGrantTest {

    @Autowired
    private MemberGrantRepository memberGrantRepository;

    @Test
    @DisplayName("create member grant for test")
    @Disabled
    public void testCreateMemberGrant() {
        MemberGrant memberGrant = MemberGrant.builder()
                .description("grant for create post by user role")
                .name("POST_CREATE_USER")
                .build();

        memberGrant = memberGrantRepository.save(memberGrant);
        log.info("member grant saved : %s".formatted(memberGrant));
        return ;
    }

    @Test
    @DisplayName("read member grant for test")
    @Disabled
    public void testReadMemberGrant() {
        long id = 1L;
        var memberGrant = memberGrantRepository.findById(id).orElseThrow();
        log.info("read grant : %s".formatted(memberGrant));
        return ;
    }

    @Test
    @DisplayName("update member grant for test")
    @Disabled
    public void testUpdateMemberGrant() {
        long id = 1L;
        var target = memberGrantRepository.findById(id).orElseThrow();
        log.info("origin member grant : %s".formatted(target));
        target.update("GRANT_FOR_UPDATE_TEST", "test grant");
        memberGrantRepository.save(target);

        var ret = memberGrantRepository.findById(id);
        log.info("update ret : %s".formatted(ret));
        return ;
    }

    @Test
    @DisplayName("delete member grant for test")
    @Disabled
    public void testDeleteMemberGrant() {
        long id = 0L;
        var target = memberGrantRepository.findById(id).orElseThrow();
        log.info("remove target memberGrant : %s".formatted(target));

        memberGrantRepository.deleteById(id);
        var ret = memberGrantRepository.findById(id);
        if(ret.isEmpty()) {
            log.info("delete success.");
        }
        return ;
    }
}
