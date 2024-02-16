package com.wagle.backend.domain.post.repository;

import com.wagle.backend.domain.post.domain.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
    Optional<Post> findByIdAndMemberId(Long postId, Long memberId); // 권한

    Page<Post> findByNameContainingIgnoreCase(Pageable pageable, String name);

    Page<Post> findAll(Pageable pageable);

    Page<Post> findByMemberId(Pageable pageable, Long memberId);

}
