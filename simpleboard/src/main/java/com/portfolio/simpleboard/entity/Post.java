package com.portfolio.simpleboard.entity;


import com.portfolio.simpleboard.entity.base.DateEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.BatchSize;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@Table(
        name="post"
        , indexes = {
                @Index(name="idx_board_id_for_post", columnList = "board_id")
}
)
public class Post extends DateEntity implements Serializable {

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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(
            referencedColumnName = "id"
            , foreignKey = @ForeignKey(name = "fk_board_for_post")
    )
    @BatchSize(size = 20)
    @ToString.Exclude
    private Board board;

    @Column
    private Boolean isDeleted;

    public void doDelete() {
        this.isDeleted = true;
    }

    public void updatePost(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void updateBoard(Board board) {
        this.board = board;
    }

    @OneToMany(
            mappedBy = "post"
            , cascade = {
                    CascadeType.ALL
        }
        , fetch = FetchType.LAZY
        , orphanRemoval = true
    )
    @Builder.Default
    private Set<PostImage> imageSet = new HashSet<>();

    public void addImage(String uuid, String fileName) {
        PostImage postImage = PostImage.builder()
                .uuid(uuid)
                .fileName(fileName)
                .post(this)
                .ord(this.imageSet.size())
                .build();
        imageSet.add(postImage);
    }

    public void clearImages() {
        imageSet.forEach(img->{
            img.changePost(null);
        });
        imageSet.clear();
    }
}
