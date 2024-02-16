package com.wagle.backend.domain.post.controller;

import com.wagle.backend.common.handler.SuccessResponse;
import com.wagle.backend.domain.member.domain.Member;
import com.wagle.backend.domain.post.dto.PostCreateDto;
import com.wagle.backend.domain.post.dto.PostUpdateDto;
import com.wagle.backend.domain.post.dto.PostsResponseDto;
import com.wagle.backend.domain.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/post")
public class PostController {
    private final PostService postService;

    /**
     * [포스트 한 개 조회]
     *
     * @param postId
     * @return
     */
    @GetMapping("/{postId}")
    public ResponseEntity<SuccessResponse> findOne(@PathVariable(value = "postId") Long postId) {
        return new ResponseEntity<>(SuccessResponse.of(postService.findOne(postId)), HttpStatus.OK);
    }

    /**
     * [특정 회원이 조회한 파일 가져오기]
     * @param member
     * @return
     */
    @GetMapping("/{postId}/my")
    public ResponseEntity<SuccessResponse> findPostsByMemberId(
            @AuthenticationPrincipal(expression = "member") Member member,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        PostsResponseDto postsResponseDto = postService.findByMemberId(pageRequest, member.getId());
        return new ResponseEntity<>(SuccessResponse.of(postsResponseDto), HttpStatus.OK);
    }

    /**
     * [포스트 수정]
     *
     * @param member
     * @param postUpdateDto
     * @return
     */
    @PutMapping("/{postId}")
    public ResponseEntity<SuccessResponse> update(@AuthenticationPrincipal(expression = "member") Member member,
                                                  @RequestBody PostUpdateDto postUpdateDto) {
        postUpdateDto.setMemberId(member.getId());
        return new ResponseEntity<>(SuccessResponse.of(postService.update(postUpdateDto)), HttpStatus.OK);
    }

    /**
     * [포스트 저장]
     *
     * @param member
     * @param postCreateDto
     * @return
     */
    @PostMapping
    public ResponseEntity<SuccessResponse> save(@AuthenticationPrincipal(expression = "member") Member member,
                                                @RequestBody PostCreateDto postCreateDto) {
        postCreateDto.setMemberId(member.getId());
        return new ResponseEntity<>(SuccessResponse.of(postService.add(postCreateDto)), HttpStatus.OK);
    }

    /**
     * [포스트 조건 검색]
     *
     * @param keyword
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/search")
    public ResponseEntity<SuccessResponse> search(@RequestParam(value = "keyword") String keyword,
                                                  @RequestParam(value = "page", defaultValue = "0") Integer page,
                                                  @RequestParam(value = "size", defaultValue = "10") Integer size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return new ResponseEntity<>(SuccessResponse.of(postService.findAll(pageRequest, keyword)), HttpStatus.OK);
    }

}
