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
public class RoleOwnGrant implements Serializable {

    @Embeddable
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @EqualsAndHashCode
    @ToString
    public class RoleOwnGrantId implements Serializable {
        @Column(nullable = false)
        private MemberRole memberRole;

        @Column(nullable = false)
        private MemberGrant memberGrant;
    }

    @EmbeddedId
    private RoleOwnGrantId roleGrantId;

}
