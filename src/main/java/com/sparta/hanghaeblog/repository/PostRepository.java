package com.sparta.hanghaeblog.repository;

import com.sparta.hanghaeblog.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByOrderByModifiedAtDesc(); // 메모 내림차순 설정

    // Optional클래스는 여러가지 API를 제공하여 null일 수도 있는 객체를 다룰 수 있도록 돕는다.
    // 'null일 수도 있는 객체'를 감싸는 일종의 Wrapper 클래스입니다.
    // 예외 : NullPointException
//    Optional<Post> findByIdAndPassword(Long id, String password);

}
