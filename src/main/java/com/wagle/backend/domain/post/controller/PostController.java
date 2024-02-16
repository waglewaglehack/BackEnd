package com.wagle.backend.domain.post.controller;

import com.wagle.backend.common.handler.SuccessResponse;
import com.wagle.backend.domain.member.domain.Member;
import com.wagle.backend.domain.post.dto.PostCreateDto;
import com.wagle.backend.domain.post.dto.PostUpdateDto;
import com.wagle.backend.domain.post.service.PostService;
import lombok.Getter;
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

    @GetMapping("/{postId}")
    public ResponseEntity<SuccessResponse> findOne(@PathVariable(value = "postId") Long postId) {
        return null;
    }

//    @GetMapping("/")
//    public ResponseEntity<SuccessResponse> findOneByUserId

    @PutMapping("/{postId}")
    public ResponseEntity<SuccessResponse> update(@AuthenticationPrincipal(expression = "member") Member member,
                                                  @RequestBody PostUpdateDto postUpdateDto) {
        postUpdateDto.setMemberId(member.getId());
        return new ResponseEntity<>(SuccessResponse.of(postService.update(postUpdateDto)), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<SuccessResponse> save(@AuthenticationPrincipal(expression = "member") Member member,
                                                @RequestBody PostCreateDto postCreateDto) {
        postCreateDto.setMemberId(member.getId());
        return new ResponseEntity<>(SuccessResponse.of(postService.add(postCreateDto)), HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<SuccessResponse> search(@RequestParam(value = "keyword") String keyword,
                                                  @RequestParam(value = "page", defaultValue = "0") Integer page,
                                                  @RequestParam(value = "size", defaultValue = "10") Integer size) {

        PageRequest pageRequest = PageRequest.of(page, size);
        return new ResponseEntity<>(SuccessResponse.of(postService.findAll(pageRequest, keyword)), HttpStatus.OK);
    }

}
