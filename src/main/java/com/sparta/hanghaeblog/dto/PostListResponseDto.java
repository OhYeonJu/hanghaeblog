package com.sparta.hanghaeblog.dto;

import com.sparta.hanghaeblog.entity.Post;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class PostListResponseDto {
    List<PostResponseDto> postList = new ArrayList<>();
    public void addPost(PostResponseDto responseDto) {
        postList.add(responseDto);
    }
}
