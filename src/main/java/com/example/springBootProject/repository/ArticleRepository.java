package com.example.springBootProject.repository;

import com.example.springBootProject.entity.Article;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface ArticleRepository extends CrudRepository<Article, Long> { // <엔티티클래스, 기본키 타입>

    @Override
    ArrayList<Article> findAll(); // Iterable => ArrayList 오버라이딩
}
