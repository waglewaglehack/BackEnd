package com.wagle.backend.domain.course.domain;

import com.wagle.backend.domain.post.domain.Post;
import jakarta.persistence.*;

@Entity
public class CoursePost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course course;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;
}
