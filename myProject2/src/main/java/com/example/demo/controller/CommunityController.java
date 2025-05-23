package com.example.demo.controller;

import com.example.demo.domain.enums.PostSortStatus;
import com.example.demo.domain.user.User;
import com.example.demo.dto.community.CommunityPostDto;
import com.example.demo.dto.community.CommunityRequest;
import com.example.demo.dto.community.PostListResponse;
import com.example.demo.dto.community.RegisterPostResponse;
import com.example.demo.service.CommunityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/community/posts")
@RequiredArgsConstructor
public class CommunityController {

    private final CommunityService communityService;

    // 게시글 등록
    @PostMapping
    public ResponseEntity<?> createPost(@RequestBody CommunityRequest request,
                                           @AuthenticationPrincipal User user) {
        try {
            Long postId = communityService.createPost(request, user);

            return ResponseEntity.ok().body(new RegisterPostResponse(postId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // 모든 게시글 조회
    @GetMapping
    public ResponseEntity<?> getAllPosts(@RequestParam(defaultValue = "RECENT") PostSortStatus sort) {
        try {
            List<CommunityPostDto> posts = communityService.getAllPosts(sort);

            return ResponseEntity.ok().body(new PostListResponse(posts));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // 특정 게시글 조회
    @GetMapping("/{id}")
    public ResponseEntity<?> getPostById(@PathVariable Long id) {

        try {
            CommunityPostDto detail = communityService.getPostById(id);

            return ResponseEntity.ok().body(detail);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // 수정
    @PutMapping("/{id}")
    public ResponseEntity<?> editPost(@PathVariable Long id,
                                           @RequestBody CommunityRequest request,
                                           @AuthenticationPrincipal User user) {
        try {
            communityService.updatePost(id, request, user);

            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<?> removePost(@PathVariable Long id,
                                           @AuthenticationPrincipal User user) {
        try {
            communityService.deletePost(id, user);

            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
