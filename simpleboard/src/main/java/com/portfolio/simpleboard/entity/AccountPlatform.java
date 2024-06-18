package com.portfolio.simpleboard.entity;

import com.portfolio.simpleboard.enums.OAuthPlatform;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
public class AccountPlatform implements Serializable {

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
