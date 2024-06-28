package com.portfolio.simpleboard.enums;


public enum OAuthPlatform {
    GOOGLE("GOOGLE")
    , NAVER( "NAVER")
    , KAKAO( "KAKAO")
    , GUEST( "GUEST")
    ;

    private String desc;

    private OAuthPlatform(String desc) {
        this.desc = desc;
    }
}
