package com.portfolio.simpleboard.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
@Builder
public class RoleOwnGrant {

    @Embeddable
    @Builder
    @Getter
    @AllArgsConstructor
    public class RoleOwnGrantId{
        @Column(nullable = false)
        private MemberRole memberRole;

        @Column(nullable = false)
        private MemberGrant memberGrant;
    }

    @EmbeddedId
    private RoleOwnGrantId roleGrantId;

}
