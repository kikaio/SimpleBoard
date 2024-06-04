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
public class PostImage implements Comparable<PostImage> {

    @Column
    private Integer ord;

    @Id
    private String uuid;

    @Column
    private String fileName;

    @ManyToOne
    @ToString.Exclude
    private Post post;

    @Override
    public int compareTo(PostImage o) {
        return this.ord - o.ord;
    }

    public void changePost(Post post) {
        this.post = post;
    }
}
