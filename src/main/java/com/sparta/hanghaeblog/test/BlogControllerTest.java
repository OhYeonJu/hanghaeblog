package com.sparta.hanghaeblog.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

// 스프링이 com.cos.blog패키지 이하를 스캔해서 모든 파일을 메모리에 new하는 것은 아니고
// 특정 어노테이션이 붙어있는 클래스 파일들을 new해서(IoC) 스프링 컨테이너에 관리해준다.
@RestController
public class BlogControllerTest {

    @GetMapping("/test/hello")
    public String hello() {
        return "<h1>Hello spring boot</h1>";
    }

    // http://localhost:8080/http/get
    @GetMapping("/http/get")
    public String getTest() {
        return "get 요청";
    }

    // http://localhost:8080/http/post
    @PostMapping("/http/post")
    public String postTest() {
        return "post 요청";
    }

    // http://localhost:8080/http/put
    @PutMapping("/http/put")
    public String putTest() {
        return "put 요청";
    }

    // http://localhost:8080/http/delete
    @DeleteMapping("/http/delete")
    public String deleteTest() {
        return "delete 요청";
    }

}
