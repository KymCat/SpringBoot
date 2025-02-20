package com.example.springBootProject.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

// DTO -> Entity 로 변환하기 위한 클래스
@Entity // 엔티티 선언
public class Article {
    @Id // PrimaryKey
    @GeneratedValue // 자동 1씩 증가...
    private Long id;

    @Column
    private String title;

    @Column
    private String content;

    // 생성자

    public Article(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
