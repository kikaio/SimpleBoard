package com.portfolio.simpleboard.service.member;

import com.portfolio.simpleboard.dto.member.RoleOwnGrantDTO;
import com.portfolio.simpleboard.repository.member.RoleOwnGrantRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class RoleOwnGrantService {

    private final RoleOwnGrantRepository roleOwnGrantRepository;

    public RoleOwnGrantDTO getRoleOwnGrant(Long id) {
        var dto = roleOwnGrantRepository.searchRoleOwnGrant(id);
        return dto;
    }
}
