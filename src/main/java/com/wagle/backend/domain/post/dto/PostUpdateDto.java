package com.wagle.backend.domain.post.dto;

import lombok.Data;

@Data
public class PostUpdateDto {
    private Long memberId; // 컨트롤러
    private Long postId;
    private String name;
    private String content;
    private Double latitude;
    private Double longitude;
}
