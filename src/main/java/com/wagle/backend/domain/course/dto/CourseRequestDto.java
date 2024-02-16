package com.wagle.backend.domain.course.dto;

import java.util.List;

public record CourseRequestDto(Long memberId, String name, String content, List<Long> postIds) {
}
