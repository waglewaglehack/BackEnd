package com.wagle.backend.domain.post.repository;

import com.wagle.backend.domain.post.domain.PostComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostCommentRepository extends JpaRepository<PostComment, Long> {

    List<PostComment> findByPostId(Long postId);
}
