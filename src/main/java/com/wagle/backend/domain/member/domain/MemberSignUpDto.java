package com.wagle.backend.domain.member.domain;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class MemberSignUpDto {
    @Email
    private String email;
    @NotBlank
    @Min(8)
    private String password;
    @NotBlank
    @Min(2)
    private String nickname;
    @NotBlank
    @Min(0)
    private int age;
}
