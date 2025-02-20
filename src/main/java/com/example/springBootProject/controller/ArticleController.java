package com.example.springBootProject.controller;

import com.example.springBootProject.dto.ArticleForm;
import com.example.springBootProject.entity.Article;
import com.example.springBootProject.repository.ArticleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
public class ArticleController {
    private static final Logger logger = LoggerFactory.getLogger(ArticleController.class);
    @Autowired // 스프링 부트가 미리 생성해 놓은 레파지토리 객체 주입(DI)
    private ArticleRepository articleRepository; // ArticleRepository 객체선언

    @GetMapping("articles/new")
    public String newArticleForm() {
        return "articles/new";
    }

    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form) {
        // toString()으로 데이터가 잘 넘어왔는지 확인
        logger.info(form.toString());

        // 1. DTO를 엔티티로 변환
        Article article = form.toEntity();
        logger.info(article.toString());

        // 2. 레파지토리 엔티티를 DB에 저장
        Article saved = articleRepository.save(article);
        logger.info(saved.toString());
        return "";
    }
}
