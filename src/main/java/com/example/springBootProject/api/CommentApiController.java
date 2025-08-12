package com.example.springBootProject.api;

import com.example.springBootProject.dto.CommentDto;
import com.example.springBootProject.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentApiController {
    private final CommentService commentService;

    // 1. 댓글 조회 -> GET
    @GetMapping("/api/articles/{articleId}/comments")
    public ResponseEntity<List<CommentDto>> comments(@PathVariable Long articleId) {
        // 서비스 호출
        List<CommentDto> dtos = commentService.comments(articleId);

        // 결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }

    // 2. 댓글 생성 -> POST
    @PostMapping("/api/articles/{articleId}/comments")
    public ResponseEntity<CommentDto> create(@PathVariable Long articleId, @RequestBody CommentDto dto) {
        // 서비스 호출
        CommentDto createdDto = commentService.create(articleId, dto);

        // 결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(createdDto);
    }

    // 3. 댓글 수정 -> PATCH
    @PatchMapping("/api/comments/{id}")
    public ResponseEntity<CommentDto> update(@PathVariable Long id,
                                             @RequestBody CommentDto dto) {

        // 서비스에 위임
        CommentDto updatedDto = commentService.update(id, dto);

        // 결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(updatedDto);
    }

    // 4. 댓글 삭제 -> DELETE
    @DeleteMapping("/api/comments/{id}")
    public ResponseEntity<CommentDto> delete(@PathVariable Long id) {
        // 서비스에 위임
        CommentDto deletedDto = commentService.delete(id);

        // 결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(deletedDto);
    }

}
