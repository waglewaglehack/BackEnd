package com.wagle.backend.domain.post.service;

import com.wagle.backend.domain.post.domain.Post;
import com.wagle.backend.domain.post.domain.PostLike;
import com.wagle.backend.domain.post.dto.PostCreateDto;
import com.wagle.backend.domain.post.dto.PostUpdateDto;
import com.wagle.backend.domain.post.repository.PostLikeRepository;
import com.wagle.backend.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Pageable;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {
    private final PostRepository postRepository;
    private final PostLikeRepository postLikeRepository;


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
    public Post findOne(Long postId) {
        return postRepository.findById(postId)
                .orElseThrow(NoSuchElementException::new); // 리펙토링 대상
    }

    /**
     * [포스트 여러 개 조회]
     * <p/> 페이지네이션을 이용한다.
     *
     * @param pageable
     * @return
     */
//    public List<Post> findAll(Pageable pageable) {
//        return postRepository.findAll(pageable);
//    }

    /**
     * [포스트 수정]
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
     * @param memberId
     * @param postId
     */
    public void likePost(Long memberId, Long postId) {
        PostLike postLike = postLikeRepository.findByMemberIdAndPostId(memberId, postId)
                .orElse(null);
        if (postLike == null) {
            postLike =  PostLike.ofPostLike(memberId, postId);
            postLikeRepository.save(postLike);
        }
    }
}
