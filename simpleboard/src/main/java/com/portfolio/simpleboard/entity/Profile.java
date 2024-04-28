package com.portfolio.simpleboard.entity;

import com.portfolio.simpleboard.entity.base.DateEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
@Builder
public class Profile extends DateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String nickname;

    @OneToOne
    @Column
    private AccountPlatform accountPlatform;
}
