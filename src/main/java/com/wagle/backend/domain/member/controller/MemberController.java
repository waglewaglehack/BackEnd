package com.wagle.backend.domain.member.controller;

import com.wagle.backend.common.handler.SuccessResponse;
import com.wagle.backend.domain.member.domain.Member;
import com.wagle.backend.domain.member.domain.MemberSignUpDto;
import com.wagle.backend.domain.member.dto.MemberNicknameDTO;
import com.wagle.backend.domain.member.repository.MemberRepository;
import com.wagle.backend.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    /**
     * [회원 가입]
     *
     * @param memberSignUpDto
     * @exception RuntimeException 회원 가입에 실패했을 시
     */
//    @PostMapping("/sign-up")
    @Deprecated
    public ResponseEntity signUp(@RequestBody MemberSignUpDto memberSignUpDto) {
        memberService.signUp(memberSignUpDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/v1/user/nickname")
    public ResponseEntity<SuccessResponse> changeNickname(@AuthenticationPrincipal(expression = "member") Member member,
                                                          @RequestBody MemberNicknameDTO dto) {
        dto.setMemberId(member.getId());
        return new ResponseEntity<>(SuccessResponse.of(memberService.changeNickname(dto)), HttpStatus.OK);
    }
}
