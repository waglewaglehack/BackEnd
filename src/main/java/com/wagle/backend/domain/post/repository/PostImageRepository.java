package com.wagle.backend.domain.post.repository;

import com.wagle.backend.domain.post.domain.PostImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostImageRepository extends JpaRepository<PostImage, Long> {
}
