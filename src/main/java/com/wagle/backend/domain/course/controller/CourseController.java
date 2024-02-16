package com.wagle.backend.domain.course.controller;

import com.wagle.backend.domain.course.domain.Course;
import com.wagle.backend.domain.course.dto.*;
import com.wagle.backend.domain.course.repository.CourseRepository;
import com.wagle.backend.domain.course.service.CourseCommentService;
import com.wagle.backend.domain.course.service.CourseLikeService;
import com.wagle.backend.domain.course.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/course")
@RequiredArgsConstructor
public class CourseController {
    private final CourseService courseService;
    private final CourseLikeService courseLikeService;
    private final CourseCommentService courseCommentService;

    @PutMapping("/like")
    public void addCourseLike(@RequestParam("course_id") Long courseId, @RequestParam("member_id")Long memberId) {
        courseLikeService.addLikeCourse(memberId, courseId);
    }

    @PostMapping("/{course_id}/comment")
    public void addCourseComment(@PathVariable(value = "course_id") Long courseId, @RequestBody CourseCommentRequestDto courseCommentRequestDto) {
        courseCommentService.addCourseComment(courseId, courseCommentRequestDto);
    }

    @GetMapping("/{course_id}/comment")
    public ResponseEntity<List<CourseCommentResponseDto>> getCourseComment(@PathVariable(value = "course_id") Long courseId) {
        return ResponseEntity.ok(courseCommentService.getCourseComment(courseId));
    }

    @GetMapping("/{course_id}")
    public ResponseEntity<CourseResponseDto> getCourse(@PathVariable(value = "course_id") Long courseId) {
        return ResponseEntity.ok(courseService.getCourse(courseId));
    }

    @GetMapping("/search")
    public Page<Course> searchCourse(@RequestParam("keyword") String keyword, @RequestParam(value = "page") Integer page,
                                     @RequestParam(value = "size") Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return courseService.searchCourse(keyword, pageable);
    }

    @PostMapping()
    public void createCourse(@RequestBody CourseRequestDto requestDto) {
        courseService.saveCourse(requestDto);
    }

    @PatchMapping("/{course_id}")
    public void updateCourse(@PathVariable(value = "course_id") Long id, @RequestBody CourseUpdateRequestDto requestDto) {
        courseService.updateCourse(id, requestDto);
    }

    @DeleteMapping("/{course_id}")
    public void deleteCourse(@PathVariable(value = "course_id")Long id) {
        courseService.deleteCourse(id);
    }
}
