package com.portfolio.simpleboard.repository.member;

import com.portfolio.simpleboard.entity.AccountPlatform;
import com.portfolio.simpleboard.enums.OAuthPlatform;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountPlatformRepository extends JpaRepository<AccountPlatform, Long> {

    public Optional<AccountPlatform> findByEmailAndPlatformType(String email, OAuthPlatform platformType);
}
