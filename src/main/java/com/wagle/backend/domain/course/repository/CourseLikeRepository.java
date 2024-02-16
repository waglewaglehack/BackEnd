package com.wagle.backend.domain.course.repository;

import com.wagle.backend.domain.course.domain.CourseLike;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CourseLikeRepository extends JpaRepository<CourseLike, Long> {
    Optional<CourseLike> findByMemberIdAndCourseId(Long memberId, Long courseId);
}
