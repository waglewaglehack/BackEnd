package com.wagle.backend.common.security.auth;

import com.wagle.backend.domain.member.domain.Member;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

@RequiredArgsConstructor
@AllArgsConstructor
@ToString
public class PrincipalDetails implements UserDetails, OAuth2User {
    public final Member member;
    private Map<String, Object> attributes;

    public Member getMember() {
        return member;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collection = new ArrayList<>();
        collection.add(() -> member.getMemberRole().name());
        return collection;
    }

    @Override
    public String getPassword() {
        return member.getPassword();
    }

    /**
     * [회원의 이메일]
     * <p/> 모든 회원은 이메일로 로그인을 해야하기 때문에 이메일을 반환한다.
     */
    @Override
    public String getUsername() {
        return member.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getName() {
        return member.getEmail();
    }

    public Long getMemberId() {
        return member.getId();
    }
}
