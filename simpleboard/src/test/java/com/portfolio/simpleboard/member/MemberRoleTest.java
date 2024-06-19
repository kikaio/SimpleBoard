package com.portfolio.simpleboard.member;


import com.portfolio.simpleboard.entity.MemberRole;
import com.portfolio.simpleboard.repository.member.MemberRoleRepository;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Log4j2
@SpringBootTest
public class MemberRoleTest {

    @Autowired
    private MemberRoleRepository memberRoleRepository;

    @Test
    @DisplayName("testSelectRole")
    @Disabled
    public void testSelectRole() {

        Long id = 1L;
        var role = memberRoleRepository.findById(id).orElseThrow();
        log.info("selected role : %s".formatted(role));
        return ;
    }

    @Test
    @DisplayName("testInsertRole")
    @Disabled
    public void testInsertRole() {
        var role = MemberRole.builder()
                .name("ROLE_TEST")
                .description("member's role for test")
                .build();
        role = memberRoleRepository.save(role);
        log.info("saved role info : %s".formatted(role));
        return ;
    }

    @Test
    @DisplayName("testUpdateRole")
    @Disabled
    public void testUpdateRole() {
        Long targetRoleId = 1L;
        var role = memberRoleRepository.findById(targetRoleId).orElseThrow();
        if(role != null) {
            role.updateInfo("ROLE_ADMIN2", "role for app admin");
            var role2 = memberRoleRepository.save(role);
            log.info("this role was updated : %s".formatted(role2));
        }
        return ;
    }

    @Test
    @DisplayName("testDeleteRole")
    @Disabled
    public void testDeleteRole() {
        Long targetId = 1L;
        var role = memberRoleRepository.findById(targetId).orElseThrow();
        if(role != null) {
            log.info("try role delete target : %s".formatted(role));
            memberRoleRepository.deleteById(targetId);

            var target = memberRoleRepository.findById(targetId);
            if(target.isEmpty()) {
                log.info("target was deleted");
            }
        }
        return ;
    }
}
