package com.wagle.backend.domain.post.repository;

import com.wagle.backend.domain.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
    Optional<Post> findByIdAndUserId(Long postId, Long memberId); // 권한

    List<Post> findAll(Pageable pageable);
}
