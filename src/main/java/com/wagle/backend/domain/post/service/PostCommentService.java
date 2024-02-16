package com.wagle.backend.domain.post.service;

import com.wagle.backend.domain.member.domain.Member;
import com.wagle.backend.domain.member.repository.MemberRepository;
import com.wagle.backend.domain.post.domain.PostComment;
import com.wagle.backend.domain.post.dto.PostCommentCreateDto;
import com.wagle.backend.domain.post.dto.PostCommentResponseDto;
import com.wagle.backend.domain.post.repository.PostCommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostCommentService {
    private final PostCommentRepository postCommentRepository;
    private final MemberRepository memberRepository;

    /**
     * [포스트 댓글 추가]
     *
     * @param postCommentCreateDto
     * @return
     */
    @Transactional
    public boolean addComment(PostCommentCreateDto postCommentCreateDto) {
        postCommentRepository.save(PostComment.of(postCommentCreateDto));
        return true;
    }

    @Transactional(readOnly = true)
    public List<PostCommentResponseDto> findAll(Long postId) {
        List<PostComment> postComments = postCommentRepository.findByPostId(postId);
        List<PostCommentResponseDto> postCommentResponseDtos = new ArrayList<>();
        for (PostComment postComment : postComments) {
            Member member = memberRepository.findById(postComment.getMemberId())
                    .orElse(Member.createAnonymous());
            postCommentResponseDtos.add(PostCommentResponseDto.of(postComment, member));
        }
        return postCommentResponseDtos;
    }
}
