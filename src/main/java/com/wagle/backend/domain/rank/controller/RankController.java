package com.wagle.backend.domain.rank.controller;

import com.wagle.backend.common.handler.SuccessResponse;
import com.wagle.backend.domain.member.domain.Member;
import com.wagle.backend.domain.rank.service.RankService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/rank")
public class RankController {

    private final RankService rankService;

    // 전체 코스 랭킹
    @GetMapping("")
    public ResponseEntity<SuccessResponse> allCourseRank() {
        return new ResponseEntity<>(SuccessResponse.of(rankService.allCourseRankSearch()), HttpStatus.OK);

    }

    // 내 코스 랭킹
    @GetMapping("/my")
    public ResponseEntity<SuccessResponse> myCourseRank() {
        Member member = Member.dummy();
        return new ResponseEntity<>(SuccessResponse.of(rankService.myCourseRankSearch(member)), HttpStatus.OK);

    }
}
