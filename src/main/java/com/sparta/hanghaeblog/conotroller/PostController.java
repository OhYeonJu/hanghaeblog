package com.sparta.hanghaeblog.conotroller;

import com.sparta.hanghaeblog.dto.*;
import com.sparta.hanghaeblog.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class PostController {

    private final PostService postService;

    // 전체 게시글 목록 조회
    // http://localhost:8080
    @GetMapping("/posts")
    public PostListResponseDto getPosts() {
        return postService.getPosts();
    }
//    이렇게 짜면 보안에 좋지 않음!
//    public List<Post> mainPage(){
//        return postService.getPost();
//    }


    // 게시글 작성
    // http://localhost:8080/api/post
    @PostMapping("/post")
    public PostResponseDto createPost(@RequestBody PostRequestDto requestDto, HttpServletRequest request) {
        return postService.createPost(requestDto, request);
    }

    // 게시글 조회
    // http://localhost:8080/api/post/1~
    @GetMapping("/post/{id}")
    public PostResponseDto getPost(@PathVariable Long id) {
        return postService.getPost(id);
    }

    // 게시글 수정
    // http://localhost:8080/api/post/1~
    @PutMapping("/post/{id}")
    public PostResponseDto updatePost(@PathVariable Long id, @RequestBody PostRequestDto requestDto, HttpServletRequest request) {
        return postService.updatePost(id, requestDto, request);
    }

    // 게시글 삭제
    // http://localhost:8080/api/post/1~
    @DeleteMapping("/post/{id}")
    public ResponseDto deletePost(@PathVariable Long id, @RequestBody PostDeleteRequestDto deleteRequestDto, HttpServletRequest request) {
        return postService.deletePost(id, deleteRequestDto, request);
    }

}
