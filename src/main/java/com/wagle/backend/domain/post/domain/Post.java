package com.wagle.backend.domain.post.domain;

import com.wagle.backend.domain.base.BaseEntity;
import com.wagle.backend.domain.member.domain.Member;
import com.wagle.backend.domain.post.dto.PostCreateDto;
import com.wagle.backend.domain.post.dto.PostUpdateDto;
import jakarta.persistence.*;
import lombok.*;

@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Entity
public class Post extends BaseEntity {
    @Id
    @GeneratedValue()
    private Long id;
    private Long memberId;
    private String name;
    @Lob
    private String content;

    // JPA 연관관계를 속도의 문제로 인해 걸지 않는다.
    private Double latitude;
    private Double longitude;

    public static Post ofPost(PostCreateDto postCreateDto) {
        return Post.builder()
                .memberId(postCreateDto.getMemberId()) // 이게 없는건 애초에 Auth가 없는거 말이 안된다.
                .name(postCreateDto.getName())
                .content(postCreateDto.getContent())
                .latitude(postCreateDto.getLatitude())
                .longitude(postCreateDto.getLongitude())
                .build();
    }

    public void update(PostUpdateDto postUpdateDto) {
        this.name = postUpdateDto.getName();
        this.content = postUpdateDto.getContent();
        this.latitude = postUpdateDto.getLatitude();
        this.longitude = postUpdateDto.getLongitude();
    }
}
