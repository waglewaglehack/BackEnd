package com.wagle.backend.domain.post.domain;

import com.wagle.backend.domain.member.domain.Member;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class PostImage {
    @Id
    @GeneratedValue
    private Long id;
    private Long memberId;
    private String savedFilename;

    public static PostImage of(Long memberId, String saveFilename) {
        return PostImage.builder()
                .memberId(memberId)
                .savedFilename(saveFilename)
                .build();
    }
}
