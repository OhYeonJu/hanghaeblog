package com.sparta.hanghaeblog.conotroller;

import com.sparta.hanghaeblog.dto.*;
import com.sparta.hanghaeblog.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    // 전체 게시글 목록 조회
    // http://localhost:8080
    @GetMapping("/api/posts")
    public PostListResponseDto getPosts() {
        return postService.getPosts();
    }
//    이렇게 짜면 보안에 좋지 않음!
//    public List<Post> mainPage(){
//        return postService.getPost();
//    }


    // 게시글 작성
    // http://localhost:8080/api/post
    @PostMapping("/api/post")
    public PostResponseDto createPost(@RequestBody PostRequestDto requestDto) {
        return postService.createPost(requestDto);
    }

    // 게시글 조회
    // http://localhost:8080/api/post/1~
    @GetMapping("/api/post/{id}")
    public PostResponseDto getPost(@PathVariable Long id) {
        return postService.getPost(id);
    }

    // 게시글 수정
    // http://localhost:8080/api/post/1~
    @PutMapping("/api/post/{id}")
    public PostResponseDto updatePost(@PathVariable Long id, @RequestBody PostRequestDto requestDto) {
        return postService.updatePost(id, requestDto);
    }

    // 게시글 삭제
    // http://localhost:8080/api/post/1~
    @DeleteMapping("/api/post/{id}")
    public ResponseDto deletePost(@PathVariable Long id, @RequestBody PostDeleteRequestDto deleteRequestDto) {
        return postService.deletePost(id, deleteRequestDto);
    }

}
