package com.wagle.backend.domain.course.repository;

import com.wagle.backend.domain.course.domain.CoursePost;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CoursePostRepository extends JpaRepository<CoursePost, Long> {
    void deleteByCourseId(Long CourseId);

    List<CoursePost> findAllByCourseId(Long CourseId);
}
