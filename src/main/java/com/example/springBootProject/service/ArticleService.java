package com.example.springBootProject.service;

import com.example.springBootProject.dto.ArticleForm;
import com.example.springBootProject.entity.Article;
import com.example.springBootProject.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;

    // 게시물 전체 조회
    public List<Article> index() {
        return articleRepository.findAll();
    }

    // 게시물 번호 조회
    public Article show(Long id) {
        return articleRepository.findById(id).orElse(null);
    }

    // 게시글 저장
    public Article create(ArticleForm form) {
        Article article = form.toEntity();
        if (article.getId() != null) // 게시글 요청 Body => id(primary key)가 포함되어 있을 경우
            return null;

        return articleRepository.save(article);
    }

    // 게시글 수정
    public Article update(Long id, ArticleForm form) {
        // 1. DTO -> 엔티티 변환하기
        Article article = form.toEntity();
        log.info("id : {}, article: {}", id, article.toString());

        // 2. 타깃 조회하기
        Article target = articleRepository.findById(id).orElse(null);

        // 3. 잘못된 요청 처리하기
        if (target == null || !id.equals(article.getId())) {
            // 400 : 잘못된 요청응답
            log.info("잘못된 요청, id : {}, article : {}",id, article.toString());
            return null;
        }

        // 4. 업데이트 및 정상 응답(200)하기
        target.patch(article);
        return articleRepository.save(target);
    }

    // 게시글 삭제
    public Article delete(Long id) {
        Article target = articleRepository.findById(id).orElse(null);
        if (target == null) {
            log.info("잘못된 요청, id : {}, 해댱 id 없음",id);
            return null;
        }

        articleRepository.delete(target);
        return target; // 삭제한 대상을 리턴
    }

    @Transactional
    public List<Article> createArticles(List<ArticleForm> dtos) {
        // 1. dto 묶음을 엔티티 묶음으로 변환하기
        List<Article> articleList = dtos.stream()
                .map(ArticleForm::toEntity)
                .toList();

        // 2. 엔티티 묶음을 DB에 저장하기
        articleList.forEach(articleRepository::save);

        // 3. 강제 예외 발생시키기
        articleRepository.findById(-1L)
                .orElseThrow(()-> new IllegalArgumentException("결제 실패!"));

        // 4. 결과 값 반환하기
        return articleList;
    }
}
