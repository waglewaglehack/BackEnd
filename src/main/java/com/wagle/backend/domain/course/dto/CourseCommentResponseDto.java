package com.wagle.backend.domain.course.dto;

import com.wagle.backend.domain.course.domain.CourseComment;
import lombok.Builder;

@Builder
public record CourseCommentResponseDto(Long courseCommentId, String writerName, String comment) {
    public CourseCommentResponseDto(CourseComment courseComment, String name) {
        this(courseComment.getId(), name, courseComment.getComment());
    }
}
