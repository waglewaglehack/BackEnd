package com.wagle.backend.domain.course.service;

import com.wagle.backend.domain.course.domain.CourseLike;
import com.wagle.backend.domain.course.repository.CourseLikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CourseLikeService {
    private final CourseLikeRepository courseLikeRepository;

    @Transactional
    public void addLikeCourse(Long memberId, Long courseId) {
        CourseLike courseLike = courseLikeRepository.findByMemberIdAndCourseId(memberId, courseId)
                .orElse(null);
        if (courseLike == null) {
            courseLike = CourseLike.ofCourseLike(memberId, courseId);
            courseLikeRepository.save(courseLike);
        }
    }
}
