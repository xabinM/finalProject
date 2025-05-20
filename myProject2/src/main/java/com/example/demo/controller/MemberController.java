package com.example.demo.controller;

import com.example.demo.domain.user.User;
import com.example.demo.dto.ErrorResponse;
import com.example.demo.dto.member.MyInfoResponse;
import com.example.demo.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/memberInfo")
    public ResponseEntity<?> lookUpMyInfo(HttpServletRequest request) {
        try {
            User user = memberService.lookupMyInfo(request);

            return ResponseEntity.ok(new MyInfoResponse(user.getName(), user.getEmail()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ErrorResponse(e.getMessage()));
        }
    }
}
