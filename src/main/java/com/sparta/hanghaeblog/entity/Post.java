package com.sparta.hanghaeblog.entity;

import com.sparta.hanghaeblog.dto.PostRequestDto;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
public class Post extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // 1씩 증가해서 자동으로 증가
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    // 연관 관계 맺고 생성자 고치기
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id") // name : 매핑할 외래키의 이름을 설정합니다.
    private Users user;


    public Post(PostRequestDto requestDto, Users user) {
        this.title = requestDto.getTitle();
//        this.username = user.getUserId(); // ??
        this.content = requestDto.getContent();
        this.user = user;
    }

    public void update(PostRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.username = requestDto.getUsername();
        this.content = requestDto.getContent();
    }
}
