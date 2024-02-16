package com.wagle.backend.domain.member.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class MemberNicknameDTO {
    @Schema(description = "변경할 닉네임")
    private String nickname;
    @Schema(hidden = true)
    private Long memberId;
}
