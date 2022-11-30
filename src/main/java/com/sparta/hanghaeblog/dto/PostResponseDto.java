package com.sparta.hanghaeblog.dto;

import com.sparta.hanghaeblog.entity.Post;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostResponseDto {
    private Long num;
    private String username;
    private String content;
    private String title;
    private LocalDateTime modifiedAt;
    private LocalDateTime createdAt;

    public PostResponseDto(Post post) {
        this.num = post.getId();
        this.username = post.getUsername();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.createdAt = post.getCreatedAt();
        this.modifiedAt = post.getModifiedAt();
    }

}
