package com.portfolio.simpleboard.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
public class MemberOwnGrant {

    @Embeddable
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public class MemberOwnGrantId {

        @Column(nullable = false)
        private Profile profile;

        @Column(nullable = false)
        private MemberGrant memberGrant;

    }

    @EmbeddedId
    private MemberOwnGrantId memberOwnGrantId;

}
