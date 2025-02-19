package com.example.springBootProject.dto;

public class ArticleForm {
    private String title; // 제목 받을 필드
    private String content; // 내용을 받을 필드

    // 전송받은 title, content를 저장할 생성자
    public ArticleForm(String title, String content) {
        this.title = title;
        this.content = content;
    }

    // 데이터를 잘 받았는지 확인할 메서드
    @Override
    public String toString() {
        return "ArticleForm{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
