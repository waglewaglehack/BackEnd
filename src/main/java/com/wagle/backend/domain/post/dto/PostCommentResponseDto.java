package com.wagle.backend.domain.post.dto;

import com.wagle.backend.domain.member.domain.Member;
import com.wagle.backend.domain.post.domain.Post;
import com.wagle.backend.domain.post.domain.PostComment;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class PostCommentResponseDto {
    private String comment;
    private String nickname;
    private String emoji;

    public static PostCommentResponseDto of(PostComment post, Member member) {
        return PostCommentResponseDto.builder()
                .comment(post.getComment())
                .nickname(member.getNickname())
                .emoji(member.getEmoji())
                .build();
    }
}
