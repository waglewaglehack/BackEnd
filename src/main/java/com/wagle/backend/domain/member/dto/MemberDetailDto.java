package com.wagle.backend.domain.member.dto;

import com.wagle.backend.domain.member.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class MemberDetailDto {
    private Long id;
    private String email;
    private String nickname;
    private String emoji;

    public static MemberDetailDto of(Member member) {
        return MemberDetailDto.builder()
                .id(member.getId())
                .emoji(member.getEmoji())
                .email(member.getEmail())
                .nickname(member.getNickname())
                .build();
    }
}
