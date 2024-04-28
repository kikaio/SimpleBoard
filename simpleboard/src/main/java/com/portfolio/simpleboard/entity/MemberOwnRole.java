package com.portfolio.simpleboard.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
@Builder
public class MemberOwnRole {

    @Embeddable
    @Getter
    @AllArgsConstructor
    public class MemberOwnRoleId {

        @Column
        private Profile profile;

        @Column
        private MemberRole memberRole;
    }

    @EmbeddedId
    private MemberOwnRoleId memberOwnRoleId;
}
