package com.example.springBootProject.api;

import com.example.springBootProject.dto.ArticleForm;
import com.example.springBootProject.entity.Article;
import com.example.springBootProject.repository.ArticleRepository;
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

    // GET : 게시글전체
    @GetMapping("/api/articles")
    public List<Article> index() {
        return articleRepository.findAll();
    }
    // GET : 단일게시글
    @GetMapping("/api/articles/{id}")
    public Article show(@PathVariable Long id) {
        return articleRepository.findById(id).orElse(null);
    }

    // POST : 게시글 작성
    @PostMapping("/api/articles")
    public Article create(@RequestBody ArticleForm form) {
        Article article = form.toEntity();
        return articleRepository.save(article);
    }
    // PATCH : 단일 게시글 수정
    @PatchMapping("api/articles/{id}")
    public ResponseEntity<Article> update(@PathVariable Long id, @RequestBody ArticleForm form) {
        // 1. DTO -> 엔티티 변환하기
        Article article = form.toEntity();
        log.info("id : {}, article: {}", id, article.toString());

        // 2. 타깃 조회하기
        Article target = articleRepository.findById(id).orElse(null);

        // 3. 잘못된 요청 처리하기
        if (target == null || id != article.getId()) {
            // 400 : 잘못된 요청응답
            log.info("잘못된 요청, id : {}, article : {}",id, article.toString());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        // 4. 업데이트 및 정상 응답(200)하기
        target.patch(article);
        Article updated = articleRepository.save(target);
        return ResponseEntity.status(HttpStatus.OK).body(updated);
    }

    // DELETE
    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Article> delete(@PathVariable Long id) {
        Article target = articleRepository.findById(id).orElse(null);
        if (target == null) {
            log.info("잘못된 요청, id : {}, 해댱 id 없음",id);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        articleRepository.delete(target);
        return ResponseEntity.status(HttpStatus.OK).build(); // build() == body(null) : body가없은 ReponseEntity 객체생성
    }
}
