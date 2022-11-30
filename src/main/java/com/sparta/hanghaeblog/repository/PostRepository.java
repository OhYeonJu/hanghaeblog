package com.sparta.hanghaeblog.repository;

import com.sparta.hanghaeblog.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByOrderByModifiedAtDesc(); // 메모 내림차순 설정
    Optional<Post> findByIdAndPassword(Long id, String password);

}
