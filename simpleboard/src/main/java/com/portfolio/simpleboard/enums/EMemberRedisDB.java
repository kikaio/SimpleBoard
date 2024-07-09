package com.portfolio.simpleboard.enums;

import lombok.Getter;

@Getter
public enum EMemberRedisDB {
    COMMON(0, "db idx for another")
    , CACHE(1, "db idx for cache")
    , SESSION(2, "db idx for session data")
    ;

    int idx;
    String desc;
    private EMemberRedisDB(int idx, String desc) {
        this.idx = idx;
        this.desc = desc;
    }
}
