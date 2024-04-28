package com.portfolio.simpleboard.entity;

import jakarta.persistence.*;

@Entity
public class MemberGrant {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String desc;
}
