package com.example.demo.controller;

import com.example.demo.domain.user.User;
import com.example.demo.dto.ErrorResponse;
import com.example.demo.dto.users.ProfileEditFormRequest;
import com.example.demo.dto.users.UserDto;
import com.example.demo.dto.users.UserProfileResponse;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class ProfileController {

    private final UserService userService;

    @GetMapping("/profile")
    public ResponseEntity<?> profile(@AuthenticationPrincipal User user) {
        try {
            UserDto userProfile = userService.getUserProfile(user);

            return ResponseEntity.ok(
                    new UserProfileResponse(userProfile)
            );
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ErrorResponse(e.getMessage()));
        }
    }

    @PutMapping("/profile")
    public ResponseEntity<?> editProfile(@AuthenticationPrincipal User user,
                                         @RequestBody ProfileEditFormRequest request) {
        try {
            userService.changeProfile(user, request);

            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ErrorResponse(e.getMessage()));
        }
    }
}
