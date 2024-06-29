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
public class MemberOwnGrant implements Serializable{

    @Embeddable
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @EqualsAndHashCode
    public static class MemberOwnGrantId implements Serializable {
        @ManyToOne(cascade = CascadeType.ALL ,fetch = FetchType.EAGER)
        @JoinColumn(referencedColumnName = "id"
                , foreignKey = @ForeignKey(name = "fk_member_profile_for_member_own_grant")
        )
        private MemberProfile memberProfile;

        @ManyToOne(cascade = CascadeType.ALL ,fetch = FetchType.EAGER)
        @JoinColumn(referencedColumnName = "id"
                , foreignKey = @ForeignKey(name = "fk_member_grant_for_member_own_grant")
        )
        private MemberGrant memberGrant;

    }

    @EmbeddedId
    private MemberOwnGrantId memberOwnGrantId;

}
