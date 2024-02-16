package com.wagle.backend.domain.post.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

@Data
@Validated
public class PostCommentCreateDto {
    private Long memberId;
    @NotBlank
    private Long postId;
    @NotBlank
    private String comment;
}
