package com.wagle.backend.domain.course.controller;

import com.wagle.backend.domain.course.domain.Course;
import com.wagle.backend.domain.course.dto.CourseRequestDto;
import com.wagle.backend.domain.course.dto.CourseResponseDto;
import com.wagle.backend.domain.course.dto.CourseUpdateRequestDto;
import com.wagle.backend.domain.course.repository.CourseRepository;
import com.wagle.backend.domain.course.service.CourseLikeService;
import com.wagle.backend.domain.course.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/v1/course")
@RequiredArgsConstructor
public class CourseController {
    private final CourseService courseService;
    private final CourseLikeService courseLikeService;

    @PutMapping("/like")
    public void addCourseLike(@RequestParam("course_id") Long courseId, @RequestParam("member_id")Long memberId) {
        courseLikeService.addLikeCourse(memberId, courseId);
    }


    @GetMapping("/{course_id}")
    public CourseResponseDto getCourse(@PathVariable(value = "course_id") Long courseId) {
        return courseService.getCourse(courseId);
    }

    @GetMapping("/search")
    public Page<Course> searchCourse(@RequestParam("keyword") String keyword, Pageable pageable) {
        if (keyword == null || keyword.equals("")) {

        }
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
