package com.wagle.backend.domain.post.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class PostsResponseDto {
    List<PostResponseDto> posts;
    int totalPages;
    int currentPage;
}
