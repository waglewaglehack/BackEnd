package com.wagle.backend.domain.post.service;

import com.wagle.backend.domain.post.domain.PostLike;
import com.wagle.backend.domain.post.repository.PostLikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostLikeService {
    private final PostLikeRepository postLikeRepository;

    /**
     * [포스트 좋아요]
     * <p/> 이미 좋아요가 있으면은 아무 행동도 하지 않는다.
     * <br/> 좋아요 취소가 없다.
     *
     * @param memberId
     * @param postId
     */
    @Transactional
    public boolean likePost(Long memberId, Long postId) {
        PostLike postLike = postLikeRepository.findByMemberIdAndPostId(memberId, postId)
                .orElse(null);
        if (postLike == null) {
            postLike = PostLike.ofPostLike(memberId, postId);
            postLikeRepository.save(postLike);
        }
        return true;
    }
}
