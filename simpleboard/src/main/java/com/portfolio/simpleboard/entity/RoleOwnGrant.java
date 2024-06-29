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
    public static class RoleOwnGrantId implements Serializable {
        @ManyToOne(cascade = CascadeType.ALL ,fetch = FetchType.EAGER)
        @JoinColumn(referencedColumnName = "id"
                , foreignKey = @ForeignKey(name = "fk_member_grant_for_role_own_grant")
        )
        private MemberGrant memberGrant;

        @ManyToOne(cascade = CascadeType.ALL ,fetch = FetchType.EAGER)
        @JoinColumn(referencedColumnName = "id"
                , foreignKey = @ForeignKey(name = "fk_member_role_for_role_own_grant")
        )
        private MemberRole memberRole;

    }

    @EmbeddedId
    private RoleOwnGrantId id;

}
