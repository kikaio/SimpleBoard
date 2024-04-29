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
    public class MemberOwnGrantId implements Serializable {
        @Column(nullable = false)
        private MemberProfile memberProfile;

        @Column(nullable = false)
        private MemberGrant memberGrant;

    }

    @EmbeddedId
    private MemberOwnGrantId memberOwnGrantId;

}
