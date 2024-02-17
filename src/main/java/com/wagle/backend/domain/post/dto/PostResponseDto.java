package com.wagle.backend.domain.post.dto;

import com.wagle.backend.domain.member.domain.Member;
import com.wagle.backend.domain.post.domain.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class PostResponseDto {
    private Long id;
    private String name;
    private String content;
    private int likeCount;
    private String nickname;
    private String emoji;
    private Double latitude;
    private Double longitude;

    public static PostResponseDto of(Post post, int likeCount, Member member) {
        return PostResponseDto.builder()
                .id(post.getId())
                .name(post.getName())
                .content(post.getContent())
                .likeCount(likeCount)
                .nickname(member.getNickname())
                .emoji(member.getEmoji())
                .latitude(post.getLatitude())
                .longitude(post.getLongitude())
                .build();
    }
}
