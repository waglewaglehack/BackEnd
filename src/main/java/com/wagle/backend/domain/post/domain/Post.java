package com.wagle.backend.domain.post.domain;

import com.wagle.backend.domain.member.domain.Member;
import jakarta.persistence.*;

@Entity
public class Post {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @Lob
    private String content;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
    private Double latitude;
    private Double longitude;
}
