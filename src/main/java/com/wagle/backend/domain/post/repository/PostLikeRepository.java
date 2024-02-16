package com.wagle.backend.domain.post.repository;

import com.wagle.backend.domain.post.domain.PostLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PostLikeRepository extends JpaRepository<PostLike, Long> {
    Optional<PostLike> findByMemberIdAndPostId(Long memberId, Long postId);

    @Query("select count(pl) from PostLike pl where pl.postId = :postId")
    int count(@Param("postId") Long postId);
}
