package com.portfolio.simpleboard.entity;


import com.portfolio.simpleboard.entity.base.DateEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Entity
@Builder
public class Post extends DateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String title;

    @Column
    private String content;

    @Column
    private String writer;

    @ManyToOne
    @Column
    private Board board;
}
