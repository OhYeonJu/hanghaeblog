package com.sparta.hanghaeblog.dto;

import com.sparta.hanghaeblog.entity.Users;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class LoginRequestDto {
    private String userId;
    private String pw;
    public LoginRequestDto(Users user) {
        this.userId = user.getUserId();
        this.pw = user.getPw();
    }
}
