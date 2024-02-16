package com.wagle.backend.domain.post.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Builder
public class PostLike {
    @Id
    @GeneratedValue
    private Long id;
    private Long memberId;
    private Long postId;

    public static PostLike ofPostLike(Long memberId, Long postId) {
        return PostLike.builder()
                .memberId(memberId)
                .postId(postId)
                .build();
    }
}
