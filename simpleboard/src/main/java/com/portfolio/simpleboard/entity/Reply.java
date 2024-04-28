package com.portfolio.simpleboard.entity;


import com.portfolio.simpleboard.entity.base.DateEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
@Builder
public class Reply extends DateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String content;

    @Column
    private String writer;

    @ManyToOne
    @Column
    private Post post;
}

