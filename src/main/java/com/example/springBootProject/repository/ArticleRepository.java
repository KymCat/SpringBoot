package com.example.springBootProject.repository;

import com.example.springBootProject.entity.Article;
import org.springframework.data.repository.CrudRepository;

public interface ArticleRepository extends CrudRepository<Article, Long> { // <엔티티클래스, 기본키 타입>

}
