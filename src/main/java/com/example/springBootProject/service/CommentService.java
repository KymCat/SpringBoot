package com.example.springBootProject.service;

import com.example.springBootProject.dto.CommentDto;
import com.example.springBootProject.entity.Article;
import com.example.springBootProject.entity.Comment;
import com.example.springBootProject.repository.ArticleRepository;
import com.example.springBootProject.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private  final CommentRepository commentRepository;
    private final ArticleRepository articleRepository;

    public List<CommentDto> comments(Long articleId) {
        // 1. 댓글 조회
        List<Comment> comments = commentRepository.findByArticleId(articleId);

        // 2. 엔티티 -> DTO 변환 및 반환
        return comments.stream()
                .map(CommentDto::createCommentDto)
                .toList();

    }

    @Transactional
    public CommentDto create(Long articleId, CommentDto dto) {
        // 1. 게시글 조회 및 예외 발생
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new IllegalArgumentException("댓글 생성 실패" + "대상 게시글이 없습니다."));

        // 2. 댓글 엔티티 생성
        Comment comment = Comment.createComment(dto, article);

        // 3. 댓글 엔티티를 DB에 저장
        Comment created = commentRepository.save(comment);

        // 4. DTO로 변환해 반환
        return CommentDto.createCommentDto(created);
    }
}
