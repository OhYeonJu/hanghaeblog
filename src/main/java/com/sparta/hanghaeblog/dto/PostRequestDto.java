package com.sparta.hanghaeblog.dto;

import com.sparta.hanghaeblog.entity.Timestamped;
import lombok.Getter;

@Getter
public class PostRequestDto {
    private Long num;
    private String title;
    private String username;
    private String password;
    private String context;
    private Timestamped createDate;
}
