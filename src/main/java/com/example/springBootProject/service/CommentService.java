package com.example.springBootProject.service;

import com.example.springBootProject.dto.CommentDto;
import com.example.springBootProject.entity.Comment;
import com.example.springBootProject.repository.ArticleRepository;
import com.example.springBootProject.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
}
