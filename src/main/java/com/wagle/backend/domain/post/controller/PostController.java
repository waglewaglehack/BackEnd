package com.wagle.backend.domain.post.controller;

import com.wagle.backend.common.handler.SuccessResponse;
import com.wagle.backend.domain.member.domain.Member;
import com.wagle.backend.domain.post.dto.PostCommentCreateDto;
import com.wagle.backend.domain.post.dto.PostCreateDto;
import com.wagle.backend.domain.post.dto.PostUpdateDto;
import com.wagle.backend.domain.post.dto.PostsResponseDto;
import com.wagle.backend.domain.post.service.PostCommentService;
import com.wagle.backend.domain.post.service.PostLikeService;
import com.wagle.backend.domain.post.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/post")
@Slf4j
public class PostController {
    private final PostService postService;
    private final PostLikeService postLikeService;
    private final PostCommentService postCommentService;

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
     *
     * @param member
     * @return
     */
    @GetMapping("/{postId}/my")
    public ResponseEntity<SuccessResponse> findPostsByMemberId(

            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size) {
        Member member = Member.dummy();
        PageRequest pageRequest = PageRequest.of(page, size);
        PostsResponseDto postsResponseDto = postService.findByMemberId(pageRequest, member.getId());
        return new ResponseEntity<>(SuccessResponse.of(postsResponseDto), HttpStatus.OK);
    }

    /**
     * [포스트 수정]
     *
     * @param postUpdateDto
     * @return
     */
    @PutMapping("/{postId}")
    public ResponseEntity<SuccessResponse> update(
            @RequestBody PostUpdateDto postUpdateDto) {
        Member member = Member.dummy();
        postUpdateDto.setMemberId(member.getId());
        return new ResponseEntity<>(SuccessResponse.of(postService.update(postUpdateDto)), HttpStatus.OK);
    }

    /**
     * [포스트 저장]
     *
     * @param postCreateDto
     * @return
     */
    @PostMapping
    public ResponseEntity<SuccessResponse> save(
            @RequestBody PostCreateDto postCreateDto,
    @RequestBody(required = false) List<MultipartFile> multipartFiles) {
        Member member = Member.dummy();
        postCreateDto.setMemberId(member.getId());
        return new ResponseEntity<>(SuccessResponse.of(postService.addPost(postCreateDto, multipartFiles)), HttpStatus.OK);
    }

    /**
     * Public [포스트 조건 검색]
     *
     * @param keyword
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/search")
    public ResponseEntity<SuccessResponse> search(@RequestParam(value = "keyword", required = false) String keyword,
                                                  @RequestParam(value = "page", defaultValue = "0") Integer page,
                                                  @RequestParam(value = "size", defaultValue = "10") Integer size) {
        log.info("PostController.search");
        PageRequest pageRequest = PageRequest.of(page, size);
        return new ResponseEntity<>(SuccessResponse.of(postService.findAll(pageRequest, keyword)), HttpStatus.OK);
    }

    /**
     * [포스트 좋아요]
     *
     * @param member
     * @param postId
     * @return
     */
    @PostMapping("/{postId}/like")
    public ResponseEntity<SuccessResponse> likePost(
            @PathVariable("postId") Long postId) {
        Member member = Member.dummy();
        return new ResponseEntity<>(SuccessResponse.of(postLikeService.likePost(member.getId(), postId)), HttpStatus.OK);
    }

    /**
     * [포스트 댓글 추가]
     *
     * @param member
     * @param postId
     * @param postCommentCreateDto
     * @return
     */
    @PostMapping("/{postId}/comment")
    public ResponseEntity<SuccessResponse> addComment(
            @PathVariable(value = "postId") Long postId,
            @RequestBody PostCommentCreateDto postCommentCreateDto) {
        Member member = Member.dummy();
        postCommentCreateDto.setMemberId(member.getId());
        return new ResponseEntity<>(SuccessResponse.of(postCommentService.addComment(postCommentCreateDto)), HttpStatus.OK);
    }

    @GetMapping("/{postId}/comment")
    public ResponseEntity<SuccessResponse> getComments(@PathVariable(value = "postId") Long postId) {
        return new ResponseEntity<>(SuccessResponse.of(postCommentService.findAll(postId)), HttpStatus.OK);
    }
}
