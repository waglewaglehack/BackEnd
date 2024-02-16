package com.wagle.backend.domain.post.controller;

import com.wagle.backend.domain.member.domain.Member;
import com.wagle.backend.domain.post.domain.Post;
import com.wagle.backend.domain.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/post")
public class PostController {
    private final PostService postService;

    @GetMapping("/{postId}")
    public ResponseEntity findOne(@PathVariable(value = "postId") Long postId) {
        Post findPost = postService.findOne(postId);
        return ResponseEntity.ok(findPost);
    }

    @PostMapping
    public ResponseEntity save(@AuthenticationPrincipal("member") Member member,
                               @RequestBody PostCrteat)
}
