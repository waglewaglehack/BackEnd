package com.wagle.backend.domain.rank.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class RankResponseDto {

    private int rank;
    private String courseName;
    private int likeCount;
}
