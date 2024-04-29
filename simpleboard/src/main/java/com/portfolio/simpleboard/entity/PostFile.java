package com.portfolio.simpleboard.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
public class PostFile implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private Integer ord;

    @Column
    private String uuid;

    @Column
    private String fileName;

    @ManyToOne
    @JoinColumn(
            referencedColumnName = "id"
            , foreignKey = @ForeignKey(name = "fk_post_for_post_file")
    )
    private Post post;
}
