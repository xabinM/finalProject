package com.example.demo.controller;

import com.example.demo.domain.user.User;
import com.example.demo.dto.comment.CommentDto;
import com.example.demo.dto.comment.CommentListResponse;
import com.example.demo.dto.comment.CommentRequest;
import com.example.demo.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/community/posts")
public class CommentController {

    private final CommentService commentService;

    // 댓글 달기
    @PostMapping("/comments/{postId}")
    public ResponseEntity<?> writeComment(@PathVariable Long postId,
                                             @RequestBody CommentRequest request,
                                             @AuthenticationPrincipal User user) {
        try {
            Long commentId = commentService.saveComment(postId, user, request);

            return ResponseEntity.ok(commentId);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // 댓글 리스트 얻기
    @GetMapping("/comments/{postId}")
    public ResponseEntity<?> getComments(@PathVariable Long postId) {
        try {
            List<CommentDto> commentDtos = commentService.getComments(postId);

            return ResponseEntity.ok(new CommentListResponse(commentDtos));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/comments/{commentId}")
    public ResponseEntity<?> editComment(@PathVariable Long commentId,
                                           @RequestBody CommentRequest request,
                                           @AuthenticationPrincipal User user) {
        try {
            commentService.updatePost(commentId, request, user);

            return ResponseEntity.noContent().build();
        }
        catch(Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // 댓글 삭제
    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable Long commentId,
                                              @AuthenticationPrincipal User user) {
        try {
            commentService.delete(user, commentId);

            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
