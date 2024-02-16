package com.wagle.backend.common.security.auth.oauth.state;

import com.wagle.backend.common.security.auth.oauth.userinfo.KakaoOAuthUserInfo;
import com.wagle.backend.common.security.auth.oauth.userinfo.OAuth2UserInfo;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class KakaoProviderState implements ProviderState{
    private final String KAKAO = "KAKAO";
    @Override
    public boolean canSupport(String provider) {
        return provider.equalsIgnoreCase(KAKAO);
    }

    @Override
    public OAuth2UserInfo getUserInfo(Map<String, Object> attr) {
        return new KakaoOAuthUserInfo(attr);
    }
}
