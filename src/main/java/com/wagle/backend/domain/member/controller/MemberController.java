package com.wagle.backend.domain.member.controller;

import com.wagle.backend.common.handler.SuccessResponse;
import com.wagle.backend.domain.member.domain.Member;
import com.wagle.backend.domain.member.domain.MemberSignUpDto;
import com.wagle.backend.domain.member.dto.MemberNicknameDTO;
import com.wagle.backend.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/user")
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

    @PutMapping("/nickname")
    public ResponseEntity<SuccessResponse> changeNickname(@AuthenticationPrincipal(expression = "member") Member member,
                                                          @RequestBody MemberNicknameDTO dto) {
        if (member == null) {
            member = Member.dummy();
        }
        dto.setMemberId(member.getId());
        return new ResponseEntity<>(SuccessResponse.of(memberService.changeNickname(dto)), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<SuccessResponse> findUserDetails(@AuthenticationPrincipal(expression = "member") Member member) {
        if (member == null) {
            member = Member.dummy();
        }
        return new ResponseEntity<>(SuccessResponse.of(memberService.findById(member.getId())), HttpStatus.OK);
    }


}
