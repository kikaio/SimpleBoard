package com.portfolio.simpleboard.redis;


import com.portfolio.simpleboard.repository.member.MemberProfileRepository;
import com.portfolio.simpleboard.repository.redis.RedisMemberSessionRepository;
import com.portfolio.simpleboard.session.MemberSession;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Log4j2
@NoArgsConstructor
public class RedisMemberSessionTest {

    @Autowired
    private MemberProfileRepository memberProfileRepository;
    @Autowired
    private RedisMemberSessionRepository redisMemberSessionRepository;

    @Test
    @DisplayName("testMemberSessionCreate")
    @Disabled
    public void testMemberSessionCreate() {
        Long targetProfileId =1L;
        var profile = memberProfileRepository.findById(targetProfileId).orElseThrow();
        var memberSession = MemberSession.fromEntity(profile);
        redisMemberSessionRepository.save(memberSession);
        return ;
    }

    @Test
    @DisplayName("testMemberSessionRead")
    @Disabled
    public void testMemberSessionRead() {
        Long profileId = 1L;
        var memberSession = redisMemberSessionRepository.findById(profileId.toString()).orElseThrow();
        log.info(memberSession);
        return ;
    }
}
