package com.portfolio.simpleboard.enums;


public enum OAuthPlatform {
    GOOGLE(1, "google")
    , NAVER(2, "naver")
    , KAKAO(3, "kakao")
    ;

    private int val;
    private String desc;

    private OAuthPlatform(int val, String desc) {
        this.val = val;
        this.desc = desc;
    }
}
