package com.example.springBootProject.repository;

import com.example.springBootProject.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    // 특정 게시글의 모든 댓글 조회
    @Query(value = "SELECT * FROM COMMENT WHERE article_id = :article_id", nativeQuery = true) // (:article_id) -> findByArticleId의 매겨변수 article_id와 매칭
    List<Comment> findByArticleId(Long article_id);

    // 특정 닉네임의 모든 댓글 조회 -> META-INF폴더 XML로 작성
    List<Comment> findByNickname(String nickname);
}
