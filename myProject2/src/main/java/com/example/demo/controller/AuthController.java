package com.example.demo.controller;

import com.example.demo.domain.user.User;
import com.example.demo.dto.*;
import com.example.demo.dto.auth.LoginMemberDto;
import com.example.demo.dto.auth.LoginRequest;
import com.example.demo.dto.auth.LoginResponse;
import com.example.demo.dto.auth.SignupRequest;
import com.example.demo.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginRequest request) {
        try {
            User user = authService.authenticate(request.getName(), request.getPassword());
            String token = authService.getToken(request.getName());

            return ResponseEntity
                    .ok(new LoginResponse(
                            new LoginMemberDto(user.getId(), user.getName(), user.getEmail()), token)
                    );
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ErrorResponse(e.getMessage()));
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody @Valid SignupRequest request) {
        try {
            authService.signup(request);

            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(new ErrorResponse(e.getMessage()));
        }
    }

    @GetMapping("/check")
    public ResponseEntity<?> checkLogin(HttpServletRequest request) {
        if (authService.isLogin(request)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }
    // 현재 브라우저에서 localStorage를 clear시키는 로그아웃 방법 사용중
//    @PostMapping("/logout")
//    public ResponseEntity<?> logout() {
//        ResponseCookie deleteCookie = ResponseCookie.from("jwt", "")
//                .httpOnly(true)
//                .secure(false)
//                .path("/")
//                .maxAge(0)
//                .sameSite("Strict")
//                .build();
//
//        return ResponseEntity
//                .ok()
//                .header(HttpHeaders.SET_COOKIE, deleteCookie.toString())
//                .build();
//    }
}
