package com.wagle.backend.domain.member.domain;

import com.wagle.backend.domain.base.BaseTimeEntity;
import com.wagle.backend.domain.member.exception.CannotChangePasswordException;
import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;

@Entity
@Getter
@Setter(value = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Slf4j
@AllArgsConstructor
@Builder
public class Member extends BaseTimeEntity {
    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;
    @Column(unique = true, updatable = false)
    private String email;
    private String password;
    private Integer age;
    private String nickname;
    private String refreshToken;
    private String provider;
    private String providerId;
    private String emoji = "\\u{1f601}";
    @Enumerated(value = EnumType.STRING)
    private MemberRole memberRole;

    public Member(String email, Integer age, MemberRole memberRole) {
        this.email = email;
        this.age = age;
        this.memberRole = memberRole;
        log.trace("[Member] 새로운 맴버 생성");
    }

    /**
     * [refreshToken 업데이트]
     */
    public void updateRefreshToken(String newRefreshToken) {
        this.refreshToken = newRefreshToken;
        log.trace("[Member] 리프래시 토큰 업데이트, 토큰 값={}", newRefreshToken);
    }

    /**
     * [비밀번호 암호화]
     * <p/>같은 비밀번호로는 변경할 수 없다.
     * @exception CannotChangePasswordException
     */
    public void changePassword(PasswordEncoder passwordEncoder, String newPassword) {
        if (passwordEncoder.matches(newPassword ,getPassword())) {
            throw new CannotChangePasswordException("같은 비밀번호로 변경할 수 없습니다.");
        }

        setPassword(passwordEncoder.encode(newPassword));
        log.info("[Member] 비밀번호 암호화, 비밀번호={}", getPassword());
    }

    public void changeNickname(String newNickname) {
        setNickname(newNickname);
    }

    public void changeMemberRole(MemberRole memberRole) {
        setMemberRole(memberRole);
    }

    public void addProviderAndId(String provider, String providerId) {
        setProvider(provider);
        setProviderId(providerId);
    }

    public static Member createAnonymous() {
        return Member.builder()
                .emoji("\\u{1f601}")
                .nickname("탈퇴한 사용자")
                .build();
    }

    public static Member dummy() {
        return Member.builder()
                .emoji("\\u{1f601}")
                .id(100L)
                .nickname("dummy")
                .build();
    }
}
