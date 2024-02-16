package com.wagle.backend.domain.course.domain;

import com.wagle.backend.domain.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Builder
public class CourseLike {
    @Id
    @GeneratedValue
    private Long id;
    private Long memberId;
    private Long courseId;

    public static CourseLike ofCourseLike(Long memberId, Long courseId) {
        return CourseLike.builder()
                .memberId(memberId)
                .courseId(courseId)
                .build();
    }
}
