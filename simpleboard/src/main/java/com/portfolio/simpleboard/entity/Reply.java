package com.portfolio.simpleboard.entity;


import com.portfolio.simpleboard.entity.base.DateEntity;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@Table(
        name="reply"
        , indexes = {
                @Index(name = "idx_post_id_for_reply", columnList = "post_id")
        }
)
public class Reply extends DateEntity implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String content;

    @Column
    private String writer;

    @ManyToOne
    @JoinColumn(
            referencedColumnName = "id"
            , foreignKey = @ForeignKey(name = "fk_post_for_reply")
    )
    @ToString.Exclude
    private Post post;

    @Column
    private Boolean isDeleted;

    public void doDelete() {
        this.isDeleted = true;
    }

    public void modify(String content){
        this.content = content;
    }
}

