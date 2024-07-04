package com.portfolio.simpleboard.dto.member;


import groovyjarjarantlr4.v4.runtime.misc.MultiMap;
import lombok.*;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@ToString
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class MemberProfileFlagDTO {

    private Long id;

    @Builder.Default
    private Map<String, Boolean> patchFlagMap = new HashMap<>();

    public Map<String, Boolean> getPatchFlagMap() {
        return this.patchFlagMap;
    }
}
