package com.wagle.backend.domain.course.service;

import com.wagle.backend.domain.course.domain.CourseComment;
import com.wagle.backend.domain.course.dto.CourseCommentRequestDto;
import com.wagle.backend.domain.course.dto.CourseCommentResponseDto;
import com.wagle.backend.domain.course.exception.CourseErrorCode;
import com.wagle.backend.domain.course.exception.CourseException;
import com.wagle.backend.domain.course.repository.CourseCommentRepository;
import com.wagle.backend.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CourseCommentService {
    private final CourseCommentRepository courseCommentRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public void addCourseComment(Long id, CourseCommentRequestDto courseCommentRequestDto) {
        CourseComment courseComment = CourseComment.builder()
                .courseId(id)
                .writerId(courseCommentRequestDto.writerId())
                .comment(courseCommentRequestDto.comment())
                .build();
        courseCommentRepository.save(courseComment);
    }

    @Transactional(readOnly = true)
    public List<CourseCommentResponseDto> getCourseComment(Long courseId) {
        List<CourseComment> courseComments = courseCommentRepository.findAllByCourseId(courseId);
        if (courseComments.isEmpty()) {
            throw new CourseException(CourseErrorCode.FAIL_FIND_COURSE_COMMENT, "코스에 코멘트가 존재하지 않습니다. course_id: %s".formatted(courseId));
        }

        return courseComments.stream()
                .map(x -> {
                    String writerName = memberRepository.findNicknameById(x.getWriterId());
                    return new CourseCommentResponseDto(x, writerName);
                })
                .collect(Collectors.toList());

    }
}
