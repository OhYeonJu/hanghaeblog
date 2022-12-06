package com.sparta.hanghaeblog.dto;

import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class PostRequestDto {
    private String username;
    private String title;
    private String content;
    private LocalDateTime registeredAt;
    private LocalDateTime unRegisteredAt;
}
