package com.wagle.backend.domain.course.repository;

import com.wagle.backend.domain.course.domain.CourseComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseCommentRepository extends JpaRepository<CourseComment, Long> {

    List<CourseComment> findAllByCourseId(Long courseId);
}
