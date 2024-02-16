package com.wagle.backend.domain.post.domain;

import com.wagle.backend.domain.post.dto.PostCommentCreateDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
@Getter
public class PostComment {
    @Id
    @GeneratedValue
    private Long id;
    private Long postId;
    private Long memberId;
    @Lob
    private String comment;

    public static PostComment of(PostCommentCreateDto postCommentCreateDto) {
        return PostComment.builder()
                .postId(postCommentCreateDto.getPostId())
                .memberId(postCommentCreateDto.getMemberId())
                .comment(postCommentCreateDto.getComment())
                .build();
    }
}
