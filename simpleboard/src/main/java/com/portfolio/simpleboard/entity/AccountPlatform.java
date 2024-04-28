package com.portfolio.simpleboard.entity;

import com.portfolio.simpleboard.enums.OAuthPlatform;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
@Builder
public class AccountPlatform {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String email;

    @Column
    @Enumerated(EnumType.STRING)
    private OAuthPlatform platformType;
}
