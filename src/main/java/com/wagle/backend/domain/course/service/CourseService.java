package com.wagle.backend.domain.course.service;

import com.wagle.backend.domain.course.domain.Course;
import com.wagle.backend.domain.course.domain.CoursePost;
import com.wagle.backend.domain.course.dto.CourseRequestDto;
import com.wagle.backend.domain.course.dto.CourseResponseDto;
import com.wagle.backend.domain.course.dto.CourseUpdateRequestDto;
import com.wagle.backend.domain.course.exception.CourseErrorCode;
import com.wagle.backend.domain.course.exception.CourseException;
import com.wagle.backend.domain.course.repository.CoursePostRepository;
import com.wagle.backend.domain.course.repository.CourseRepository;
import com.wagle.backend.domain.member.repository.MemberRepository;
import com.wagle.backend.domain.post.dto.PostCourseResponseDto;
import com.wagle.backend.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class CourseService {
    private final CourseRepository courseRepository;
    private final CoursePostRepository coursePostRepository;
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    public CourseResponseDto getCourse(Long id) {
        Optional<Course> course = courseRepository.findById(id);
        if (course.isEmpty()) {
            throw new CourseException(CourseErrorCode.FAIL_FIND_COURSE, "존재하지 않는 코스입니다. course_id : %s".formatted(id));
        }

        return CourseResponseDto.builder()
                .courseId(id)
                .memberId(course.get().getMemberId())
                .name(course.get().getName())
                .content(course.get().getContent())
                .postCourseResponseDtoList(coursePostRepository.findAllByCourseId(id).stream()
                        .map(CoursePost::getPostId)
                        .map(postRepository::findById)
                        .filter(Optional::isPresent)
                        .map(Optional::get)
                        .map(post -> {
                            String nickName = memberRepository.findNicknameById(post.getMemberId());
                            return new PostCourseResponseDto(post, nickName);
                        })
                        .collect(Collectors.toList()))
                .build();
    }

    public Page<Course> searchCourse(String keyword, Pageable pageable) {
        return courseRepository.findByNameContainingIgnoreCase(keyword, pageable);
    }

    @Transactional
    public void saveCourse(CourseRequestDto requestDto) {
        Course course = Course.builder()
                .name(requestDto.name())
                .content(requestDto.content())
                .memberId(requestDto.memberId())
                .build();

        Course savedCourse = courseRepository.save(course);

        for (Long postId : requestDto.postIds()) {
            CoursePost coursePost = CoursePost.builder()
                    .courseId(savedCourse.getId())
                    .postId(postId)
                    .build();

            coursePostRepository.save(coursePost);
        }
    }

    @Transactional
    public void updateCourse(Long id, CourseUpdateRequestDto requestDto) {
        Optional<Course> course = courseRepository.findById(id);
        if (course.isEmpty()) {
            throw new CourseException(CourseErrorCode.FAIL_FIND_COURSE, "존재하지 않는 코스입니다. course_id : %s".formatted(id));
        }
        course.get().updateCourse(requestDto.name(), requestDto.content());
    }

    @Transactional
    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
        coursePostRepository.deleteByCourseId(id);
    }

    @Transactional(readOnly = true)
    public List<Course> getCourseAll() {
        return courseRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Course> getCourseByMemberId(Long memberId) {
        return courseRepository.findByMemberId(memberId);
    }
}
