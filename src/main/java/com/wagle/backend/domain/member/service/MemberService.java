package com.wagle.backend.domain.member.service;

import com.wagle.backend.domain.course.repository.CourseRepository;
import com.wagle.backend.domain.member.domain.Member;
import com.wagle.backend.domain.member.domain.MemberRole;
import com.wagle.backend.domain.member.domain.MemberSignUpDto;
import com.wagle.backend.domain.member.dto.MemberDetailDto;
import com.wagle.backend.domain.member.dto.MemberNicknameDTO;
import com.wagle.backend.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;


@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final CourseRepository courseRepository;

    public void signUp(MemberSignUpDto userSignUpDto) {
        // TODO 회원 가입 정책 추가 필요

        Member newMember = new Member(userSignUpDto.getEmail(), userSignUpDto.getAge(), MemberRole.ROLE_USER);
        newMember.changePassword(passwordEncoder, userSignUpDto.getPassword());
        newMember.changeNickname(userSignUpDto.getNickname());

        memberRepository.save(newMember);
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean changeNickname(MemberNicknameDTO dto) {
        Member member = memberRepository.findById(dto.getMemberId())
                .orElseThrow(IllegalArgumentException::new);
        member.changeNickname(dto.getNickname());
        memberRepository.save(member);
        return true;
    }

    @Transactional(readOnly = true)
    public MemberDetailDto findById(Long memberId) {
        return MemberDetailDto.of(memberRepository.findById(memberId)
                .orElseThrow(NoSuchElementException::new));
    }
}
