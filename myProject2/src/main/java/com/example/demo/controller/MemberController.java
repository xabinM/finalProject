package com.example.demo.controller;

import com.example.demo.domain.user.User;
import com.example.demo.dto.ErrorResponse;
import com.example.demo.dto.users.ProfileEditFormRequest;
import com.example.demo.dto.users.UserProfileResponse;
import com.example.demo.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/profile")
    public ResponseEntity<?> profile(@AuthenticationPrincipal User user) {
        try {
            return ResponseEntity.ok(
                    new UserProfileResponse(user.getName(), user.getEmail(), user.getBirthDate(),
                            user.getGender(), user.getNickname(), user.getProfileImage())
            );
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ErrorResponse(e.getMessage()));
        }
    }

    @PutMapping("/profile")
    public ResponseEntity<?> editProfile(@AuthenticationPrincipal User user,
                                         @RequestBody ProfileEditFormRequest request) {
        try {
            memberService.changeProfile(user.getId(), request);

            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ErrorResponse(e.getMessage()));
        }
    }
}
