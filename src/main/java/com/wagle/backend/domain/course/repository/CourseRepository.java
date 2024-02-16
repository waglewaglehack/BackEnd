package com.wagle.backend.domain.course.repository;

import com.wagle.backend.domain.course.domain.Course;
import com.wagle.backend.domain.member.domain.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {

    Page<Course> findByNameContainingIgnoreCase(String name, Pageable pageable);
}
