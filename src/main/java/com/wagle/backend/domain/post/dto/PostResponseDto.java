package com.wagle.backend.domain.post.dto;

import lombok.Data;

@Data
public class PostResponseDto {
    private Long id;
    private String name;
    private String content;
    private Integer likeCount;
    private String nickname;
}
