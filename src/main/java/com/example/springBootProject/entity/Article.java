package com.example.springBootProject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

// DTO -> Entity 로 변환하기 위한 클래스
@AllArgsConstructor
@NoArgsConstructor // 매개변수 없는 기본 생성자
@ToString
@Entity // 엔티티 선언
@Getter
public class Article {
    @Id // PrimaryKey
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 자동 1씩 증가... 전략 : DB에서알아서
    private Long id;

    @Column
    private String title;

    @Column
    private String content;

}
