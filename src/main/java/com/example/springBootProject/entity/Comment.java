package com.example.springBootProject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // DB가 자동으로 1씩 증가
    private Long id; // 기본키

    @ManyToOne // Comment 엔티티와 Article 엔티티를 다대일 관계로 설정
    @JoinColumn(name="article_id") // 외래키 생성, Article 엔티티으 기본키(id)와 매핑
    private Article article; // 해당 댓글의 부모 게시글

    @Column
    private  String nickname; // 댓글을 단 사람

    @Column
    private String body; // 댓글 본문
}
