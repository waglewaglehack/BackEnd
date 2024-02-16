package com.wagle.backend.domain.post.service;

import com.wagle.backend.domain.member.domain.Member;
import com.wagle.backend.domain.member.repository.MemberRepository;
import com.wagle.backend.domain.post.domain.Post;
import com.wagle.backend.domain.post.domain.PostLike;
import com.wagle.backend.domain.post.dto.PostCreateDto;
import com.wagle.backend.domain.post.dto.PostResponseDto;
import com.wagle.backend.domain.post.dto.PostUpdateDto;
import com.wagle.backend.domain.post.dto.PostsResponseDto;
import com.wagle.backend.domain.post.repository.PostLikeRepository;
import com.wagle.backend.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {
    private final PostRepository postRepository;
    private final PostLikeRepository postLikeRepository;
    private final MemberRepository memberRepository;


    @Transactional
    public boolean add(PostCreateDto postCreateDto) { // true이거나 exception 이니까 그냥 true로 한다.
        Post post = Post.ofPost(postCreateDto);
        postRepository.save(post);
        return true;
    }

    /**
     * [포스트 하나 조회]
     *
     * @param postId
     * @return
     */
    public PostResponseDto findOne(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(NoSuchElementException::new);// 리펙토링 대상
        int count = postLikeRepository.count(post.getId());
        Member member = memberRepository.findById(post.getMemberId())
                .orElseThrow(NoSuchElementException::new);
        return PostResponseDto.of(post, count, member);
    }


    /**
     * [포스트 여러 개 조회]
     * <p/> 페이지네이션을 이용한다.
     * <br/> keyWord가 없으면 없는대로 검색을 한다.
     *
     * @param pageable
     * @param keyword
     * @return
     */
    // TODO 1 + N + N  해결하기
    public PostsResponseDto findAll(Pageable pageable, String keyword) {
        Page<Post> postPage = null;
        // 페이지 조회
        if (keyword == null) {
            postPage = postRepository.findAll(pageable);
        } else {
            postPage = postRepository.findByNameContainingIgnoreCase(pageable, keyword);
        }
        // DTO 매핑
        return toPostsResponseDto(postPage);
    }

    /**
     * [맴버의 포스트 가져오기]
     * @param pageable
     * @param memberId
     * @return
     */
    public PostsResponseDto findByMemberId(Pageable pageable, Long memberId) {
        return toPostsResponseDto(postRepository.findByMemberId(pageable, memberId));
    }

    /**
     * [포스트 수정]
     *
     * @param postUpdateDto
     * @return
     */
    @Transactional
    public boolean update(PostUpdateDto postUpdateDto) {
        Post post = postRepository.findByIdAndMemberId(postUpdateDto.getPostId(), postUpdateDto.getMemberId())
                .orElseThrow(NoSuchElementException::new);
        post.update(postUpdateDto);
        postRepository.save(post);
        return true;
    }

    /**
     * [포스트 좋아요]
     * <p/> 이미 좋아요가 있으면은 아무 행동도 하지 않는다.
     * <br/> 좋아요 취소가 없다.
     *
     * @param memberId
     * @param postId
     */
    public void likePost(Long memberId, Long postId) {
        PostLike postLike = postLikeRepository.findByMemberIdAndPostId(memberId, postId)
                .orElse(null);
        if (postLike == null) {
            postLike = PostLike.ofPostLike(memberId, postId);
            postLikeRepository.save(postLike);
        }
    }

    private PostsResponseDto toPostsResponseDto(Page<Post> postPage) {
        List<PostResponseDto> postResponseDtos = new ArrayList<>();
        for (Post post : postPage) {
            int count = postLikeRepository.count(post.getId());
            Member member = memberRepository.findById(post.getMemberId())
                    .orElseThrow(NoSuchElementException::new);
            postResponseDtos.add(PostResponseDto.of(post, count, member));
        }

        int totalPages = postPage.getTotalPages();
        int currentPage = postPage.getNumber();

        return PostsResponseDto.builder()
                .posts(postResponseDtos)
                .totalPages(totalPages)
                .currentPage(currentPage)
                .build();
    }
}
