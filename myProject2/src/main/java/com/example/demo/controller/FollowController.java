package com.example.demo.controller;

import com.example.demo.domain.user.User;
import com.example.demo.dto.follow.FollowDto;
import com.example.demo.dto.follow.FollowListResponse;
import com.example.demo.service.FollowService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/follows")
public class FollowController {

    private final FollowService followService;

    @PostMapping("/{pharmacistId}")
    public ResponseEntity<?> followPharmacist(@PathVariable Long pharmacistId,
                                       @AuthenticationPrincipal User user) {
        try {
            followService.follow(user, pharmacistId);

            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/my")
    public ResponseEntity<?> getMyFollows(@AuthenticationPrincipal User user) {
        try {
            List<FollowDto> follows = followService.getMyFollows(user.getId());

            return ResponseEntity.ok().body(new FollowListResponse(follows));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{pharmacistId}")
    public ResponseEntity<?> cancelFollow(@AuthenticationPrincipal User user, @PathVariable Long pharmacistId) {
        try {
            followService.deleteFollow(user, pharmacistId);

            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
