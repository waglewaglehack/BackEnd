package com.wagle.backend.domain.rank.dto;

import lombok.Data;

@Data
public class RankResponseDto {

    private int rank;
    private String courseName;
    private int likeCount;
}
