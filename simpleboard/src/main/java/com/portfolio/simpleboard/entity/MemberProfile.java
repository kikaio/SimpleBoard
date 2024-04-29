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
public class MemberProfile extends DateEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String nickname;

    @OneToOne
    @JoinColumn(
            referencedColumnName = "id"
            , foreignKey = @ForeignKey(name = "fk_accoutn_platform_for_member_profile")
    )
    private AccountPlatform accountPlatform;
}
