package com.wagle.backend.domain.member.service;

import com.wagle.backend.domain.member.domain.Member;
import com.wagle.backend.domain.member.domain.MemberRole;
import com.wagle.backend.domain.member.domain.MemberSignUpDto;
import com.wagle.backend.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    public void signUp(MemberSignUpDto userSignUpDto){
        // TODO 회원 가입 정책 추가 필요

        Member newMember = new Member(userSignUpDto.getEmail(), userSignUpDto.getAge(), MemberRole.ROLE_USER);
        newMember.changePassword(passwordEncoder, userSignUpDto.getPassword());
        newMember.changeNickname(userSignUpDto.getNickname());

        memberRepository.save(newMember);
    }
}
