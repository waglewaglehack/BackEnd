package com.wagle.backend.common.security.auth.oauth.userinfo;

import java.util.Map;

public class KakaoOAuthUserInfo implements OAuth2UserInfo{
    String providerId;
    String provider;
    String username;
    String email;

    public KakaoOAuthUserInfo(Map<String, Object> attr) {
        this.providerId = (String) attr.get("id");
//        this.email = (String) attr.get("email"); // 현재 이메일을 받을 수 없는 상태
        this.email = null;
        this.provider = "KAKAO";
        this.username = (String) attr.get("nickname");
    }
    @Override
    public String getProviderId() {
        return this.providerId;
    }

    @Override
    public String getProvider() {
        return this.provider;
    }

    @Override
    public String getEmail() {
        return this.email;
    }

    @Override
    public String getUsername() {
        return this.username;
    }
}
