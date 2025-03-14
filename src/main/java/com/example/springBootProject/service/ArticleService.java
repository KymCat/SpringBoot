package com.example.springBootProject.service;

import com.example.springBootProject.dto.ArticleForm;
import com.example.springBootProject.entity.Article;
import com.example.springBootProject.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;

    public List<Article> index() { // 게시물 전체 조회
        return articleRepository.findAll();
    }

    public Article show(Long id) { // 게시물 번호 조회
        return articleRepository.findById(id).orElse(null);
    }


    public Article create(ArticleForm form) { // 게시글 저장
        Article article = form.toEntity();
        if (article.getId() != null) // 게시글 요청 Body => id(primary key)가 포함되어 있을 경우
            return null;

        return articleRepository.save(article);
    }

    public Article update(Long id, ArticleForm form) { // 게시글 수정
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

    public Article delete(Long id) { // 게시글 삭제
        Article target = articleRepository.findById(id).orElse(null);
        if (target == null) {
            log.info("잘못된 요청, id : {}, 해댱 id 없음",id);
            return null;
        }

        articleRepository.delete(target);
        return target; // 삭제한 대상을 리턴
    }
}
