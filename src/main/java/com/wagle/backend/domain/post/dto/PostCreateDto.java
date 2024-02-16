package com.wagle.backend.domain.post.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

@Data
@Validated
public class PostCreateDto {
    private Long memberId; // 컨트롤러
    @NotBlank
    private String name;
    @NotBlank
    private String content;
    @NotBlank
    private Double latitude;
    @NotBlank
    private Double longitude;
}
