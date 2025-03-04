package com.example.springBootProject.dto;

import com.example.springBootProject.entity.Article;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor // 생성자 자동생성
@ToString // ToString() 메서드 자동생성
public class ArticleForm {
    private Long id;
    private String title; // 제목 받을 필드
    private String content; // 내용을 받을 필드


    public Article toEntity() {
        return new Article(id, title, content);
    }
}
