package com.sparta.hanghaeblog.dto;

import lombok.*;

import javax.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class SignupRequestDto {

    @Size(min = 4, max = 10)
//    @Pattern(regexp = "[a-z0-9]") // [,] 사이의 문자 중 하나를 선택한다. -는 범위지정이다.
    private String userId;

    @Size(min = 8, max = 15)
//    @Pattern(regexp = "[a-zA-z0-9]")
    private String pw;

}
