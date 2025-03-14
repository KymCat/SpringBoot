package com.example.springBootProject.api;

import com.example.springBootProject.dto.ArticleForm;
import com.example.springBootProject.entity.Article;
import com.example.springBootProject.repository.ArticleRepository;
import com.example.springBootProject.service.ArticleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
public class ArticleApiController {
    private final ArticleRepository articleRepository;
    private final ArticleService articleService;

    // GET : 게시글전체
    @GetMapping("/api/articles")
    public List<Article> index() {
        return articleService.index();
    }

    // GET : 단일게시글
    @GetMapping("/api/articles/{id}")
    public Article show(@PathVariable Long id) {
        return articleService.show(id);
    }

    // POST : 게시글 작성
    @PostMapping("/api/articles")
    public ResponseEntity<Article> create(@RequestBody ArticleForm form) {
        Article created = articleService.create(form);
        return (created != null) ?
                ResponseEntity.status(HttpStatus.OK).body(created) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    // PATCH : 단일 게시글 수정
    @PatchMapping("api/articles/{id}")
    public ResponseEntity<Article> update(@PathVariable Long id, @RequestBody ArticleForm form) {

        Article updated = articleService.update(id, form);
        return (updated != null) ?
                ResponseEntity.status(HttpStatus.OK).body(updated) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    // DELETE
    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Article> delete(@PathVariable Long id) {
        Article deleted = articleService.delete(id); // null : 삭제 Error

        return (deleted != null) ?
                ResponseEntity.status(HttpStatus.OK).build() :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }
}
