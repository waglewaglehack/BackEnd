package com.wagle.backend.domain.member.domain;

import org.junit.jupiter.api.Test;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;


@Transactional
class MemberTest {
    @Test
    void createAnonymous_success() {
        Member member = Member.createAnonymous();
        assertThat(member.getEmoji()).isEqualTo("\\u{1f601}");
    }

}