package com.wagle.backend.domain.course.dto;

import com.wagle.backend.domain.course.domain.Course;
import lombok.Builder;

@Builder
public record CourseStartRequestDto(Long id, String name, String content, Integer like, String emoge) {
    public CourseStartRequestDto(Course course, Integer likeCount) {
        this(course.getId(), course.getName(), course.getContent(), likeCount, "\uD83D\uDE0A");
    }
}
