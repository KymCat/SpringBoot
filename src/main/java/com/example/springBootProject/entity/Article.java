package com.example.springBootProject.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.ToString;

// DTO -> Entity 로 변환하기 위한 클래스
@AllArgsConstructor
@ToString
@Entity // 엔티티 선언
public class Article {
    @Id // PrimaryKey
    @GeneratedValue // 자동 1씩 증가...
    private Long id;

    @Column
    private String title;

    @Column
    private String content;

}
