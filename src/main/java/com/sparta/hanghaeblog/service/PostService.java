package com.sparta.hanghaeblog.service;

import com.sparta.hanghaeblog.dto.*;
import com.sparta.hanghaeblog.entity.Post;
import com.sparta.hanghaeblog.entity.Users;
import com.sparta.hanghaeblog.jwt.JwtUtil;
import com.sparta.hanghaeblog.repository.PostRepository;
import com.sparta.hanghaeblog.repository.UserRepository;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    // 전체 포스트 보여주기
    @Transactional(readOnly = true)
    public PostListResponseDto getPosts() {
        PostListResponseDto postListResponseDto = new PostListResponseDto();

        List<Post> posts = postRepository.findAllByOrderByModifiedAtDesc(); // 최신순
        for (Post post : posts) {
            postListResponseDto.addPost(new PostResponseDto(post));
        }

        return postListResponseDto;
    }
//    Dto에 담아서 반환하기
//    public List<Post> getPost() {
//        return postRepository.findAllByOrderByModifiedAtDesc(); // 저장된 데이터들을 다 가져올 수 있다.
//    }

    // 포스트 생성
    @Transactional
    public PostCreateResponseDto createPost(PostRequestDto requestDto, HttpServletRequest request) {
        Users user = userTokenCheck(request);

        Post post = new Post(requestDto, user.getUserId());
        postRepository.save(post); // 자동으로 쿼리가 생성되면서 데이터베이스에 연결되며 저장된다.

        return new PostCreateResponseDto(post, user.getUserId());

    }

    // 선택 포스트 가져오기
    @Transactional(readOnly = true)
    public PostResponseDto getPost(Long id) {
        Post post = checkPost(id);

        return new PostResponseDto(post);
    }

    // 포스트 수정
    // 토큰을 검사하여, 유효한 토큰일 경우에만 게시글 수정 가능
    @Transactional
    public PostResponseDto updatePost(Long id, PostRequestDto requestDto, HttpServletRequest request) {
        Users user = userTokenCheck(request);

        Post post = checkPost(id);
        if (userRepository.existsByUserId(user.getUserId())) {
            post.update(requestDto, user.getUserId());
            postRepository.save(post);

            return new PostResponseDto(post);
        } else {
            throw new IllegalArgumentException("작성자가 일치하지 않습니다.");
        }
    }

    // 포스트 삭제
    @Transactional
    public ResponseDto deletePost(Long id, HttpServletRequest request) {
        Users user = userTokenCheck(request);
        Post post = checkPost(id);

        if (userRepository.existsByUserId(user.getUserId())){
            postRepository.delete(post);

            return new ResponseDto("포스트 삭제 성공", HttpStatus.OK.value());
        } else {
            throw new IllegalArgumentException("작성자가 일치하지 않습니다.");
        }
    }

    // 포스트 번호를 체크해서 번호가 없으면 에러메세지 출력
    private Post checkPost(Long id) {
        return postRepository.findById(id).orElseThrow(
                () -> new RuntimeException("포스트가 존재하지 않습니다.")
        );
    }

    private Users userTokenCheck(HttpServletRequest request) {
        String token = jwtUtil.resolveToken(request);
        Claims claims;

        if(token != null) {
            if (jwtUtil.validateToken(token)) {
                // 토큰에서 사용자 정보 가져오기
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("Token Error");
            }

            String username = claims.getSubject();
            // 토큰에서 가져온 사용자 정보를 사용하여 DB 조회
            Users user = userRepository.findByUserId(username).orElseThrow(
                    () -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
            );

            return user;
        }
        throw new IllegalArgumentException("Token Error");

    }


}
