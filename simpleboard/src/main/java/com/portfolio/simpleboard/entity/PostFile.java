package com.portfolio.simpleboard.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
@Builder
public class PostFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private Integer order;

    @Column
    private String uuid;

    @Column
    private String fileName;

    @ManyToOne
    @Column
    private Post post;
}
