package com.example.springBootProject.api;

import com.example.springBootProject.entity.Article;
import com.example.springBootProject.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ArticleApiController {
    private final ArticleRepository articleRepository;

    // GET
    @GetMapping("/api/articles")
    public List<Article> index() {
        return articleRepository.findAll();
    }
    // POST
    // PATCH
    // DELETE
}
