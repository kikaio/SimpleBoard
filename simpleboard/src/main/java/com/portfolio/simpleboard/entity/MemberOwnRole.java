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
public class MemberOwnRole implements Serializable {

    @Embeddable
    @Setter
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @EqualsAndHashCode
    @ToString
    public static class MemberOwnRoleId implements Serializable{

        @OneToOne(cascade = CascadeType.ALL ,fetch = FetchType.EAGER)
        @JoinColumn(referencedColumnName = "id"
                , foreignKey = @ForeignKey(name = "fk_member_profile_for_member_own_role")
        )
        private MemberProfile memberProfile;

        @OneToOne(cascade = CascadeType.ALL ,fetch = FetchType.EAGER)
        @JoinColumn(referencedColumnName = "id"
                , foreignKey = @ForeignKey(name = "fk_member_role_for_member_own_role")
        )
        private MemberRole memberRole;
    }

    @EmbeddedId
    private MemberOwnRoleId memberOwnRoleId;
}
