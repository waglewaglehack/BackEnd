package com.wagle.backend.domain.course.domain;

import com.wagle.backend.domain.base.BaseEntity;
import com.wagle.backend.domain.base.BaseTimeEntity;
import com.wagle.backend.domain.member.domain.Member;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
public class Course extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long memberId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String content;

    public void updateCourse(String name, String content) {
        this.name = name;
        this.content = content;
    }
}
