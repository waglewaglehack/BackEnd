package com.wagle.backend.domain.member.repository;

import com.wagle.backend.domain.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(String email);

    Optional<Member> findByNickname(String nickname);

    Optional<Member> findByRefreshToken(String refreshToken);

    Optional<Member> findByProviderId(String providerId);
    @Query("SELECT m.nickname FROM Member m WHERE m.id = :id")
    String findNicknameById(@Param("id") Long id);

}
