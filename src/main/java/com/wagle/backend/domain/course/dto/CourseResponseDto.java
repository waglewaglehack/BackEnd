package com.wagle.backend.domain.course.dto;

import com.wagle.backend.domain.post.domain.Post;
import com.wagle.backend.domain.post.dto.PostCourseResponseDto;
import com.wagle.backend.domain.post.dto.PostResponseDto;
import lombok.Builder;

import java.util.List;

@Builder
public record CourseResponseDto(Long courseId, Long memberId, String name, String content, List<PostCourseResponseDto> postCourseResponseDtoList, Integer likeCount) {
}
