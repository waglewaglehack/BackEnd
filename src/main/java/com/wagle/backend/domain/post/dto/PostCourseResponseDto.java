package com.wagle.backend.domain.post.dto;

import com.wagle.backend.domain.post.domain.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
@Data
@AllArgsConstructor
@Builder
public class PostCourseResponseDto {
    private Long postId;
    private String name;
    private String content;
    private String memberName;

    public PostCourseResponseDto(Post post, String nickName) {
        this.postId = post.getId();
        this.name = post.getName();
        this.content = post.getContent();
        this.memberName = nickName;
    }
}