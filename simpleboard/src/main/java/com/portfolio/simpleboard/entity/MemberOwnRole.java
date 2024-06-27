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

        @Column(nullable = false)
        private MemberProfile memberProfile;

        @Column(nullable = false)
        private MemberRole memberRole;
    }

    @EmbeddedId
    private MemberOwnRoleId memberOwnRoleId;
}
