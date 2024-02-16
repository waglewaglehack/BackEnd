package com.wagle.backend.common.security.auth.oauth.state;

import com.wagle.backend.common.security.auth.oauth.userinfo.OAuth2UserInfo;
import com.wagle.backend.common.security.auth.oauth.userinfo.GoogleOAuthUserInfo;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class GoogleProviderState implements ProviderState {

    private final String GOOGLE = "Google";

    @Override
    public boolean canSupport(String provider) {
        return provider.equalsIgnoreCase(GOOGLE);
    }

    @Override
    public OAuth2UserInfo getUserInfo(Map<String, Object> attr) {
        return new GoogleOAuthUserInfo(attr);
    }
}
