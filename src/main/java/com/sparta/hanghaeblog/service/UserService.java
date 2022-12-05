package com.sparta.hanghaeblog.service;

import com.sparta.hanghaeblog.dto.*;
import com.sparta.hanghaeblog.entity.Post;
import com.sparta.hanghaeblog.entity.Users;
import com.sparta.hanghaeblog.jwt.JwtUtil;
import com.sparta.hanghaeblog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    // 트랜잭션 : 모든 작업들이 성공적으로 완료되어야 작업 묶음의 결과를 적용하고, 어떤 작업에서 오류가 발생했을 때는 이전에 있던 모든 작업들이 성공적이었더라도 없었던 일처럼 완전히 되돌리는 것이 트랜잭션의 개념이다.
    // 일련의 작업들을 묶어서 하나의 단위로 처리하고 싶다면 @Transactional을 활용하자.
    @Transactional
    public ResponseDto signup(SignupRequestDto signupRequestDto){
        String userId = signupRequestDto.getUserId();
        String userPw = signupRequestDto.getPw();

        // 아이디 중복 확인
        Optional<Users> found = userRepository.findByUserId(userId);

        if(found.isPresent()){
            throw new IllegalArgumentException("중복된 아이디가 존재합니다.");
        } else {
            Users user = new Users(userId, userPw);
            userRepository.save(user);

            return new ResponseDto("회원가입 성공", HttpStatus.OK.value());
        }
    }

    @Transactional(readOnly = true)
    public ResponseDto login(LoginRequestDto loginRequestDto, HttpServletResponse response) {
        String userId = loginRequestDto.getUserId();
        String pw = loginRequestDto.getPw();

        // 사용자 확인
        Users user = userRepository.findByUserId(userId).orElseThrow(
                () -> new IllegalArgumentException("등록된 사용자가 없습니다.")
        );

        // 비밀번호 확인
        if(!pw.equals(user.getPw())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, jwtUtil.createToken(user.getUserId()));

        return new ResponseDto("로그인 성공", HttpStatus.OK.value());
    }
}
