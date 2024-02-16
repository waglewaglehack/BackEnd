package com.wagle.backend.domain.course.service;

import com.wagle.backend.domain.course.domain.Course;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseDomainService {
    private final CourseService courseService;
    private final CourseLikeService courseLikeService;

    @Transactional(readOnly = true)
    public List<Course> getCourseAll() {
        return courseService.getCourseAll();
    }

    @Transactional
    public List<Course> getCourseByMemberId(Long memberId) {
        return courseService.getCourseByMemberId(memberId);
    }

    @Transactional(readOnly = true)
    public int getCourseLikeCount(Long courseId) {
        return courseLikeService.getLikeCount(courseId);
    }
}
