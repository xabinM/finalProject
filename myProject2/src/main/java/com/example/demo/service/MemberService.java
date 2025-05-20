package com.example.demo.service;

import com.example.demo.domain.user.User;
import com.example.demo.exception.Exception;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.JwtTokenProvider;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;

    public User lookupMyInfo(HttpServletRequest request) {
        String token = jwtTokenProvider.resolveToken(request);

        Long id = Long.valueOf(jwtTokenProvider.getUserIdFromToken(token));

        User user = userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException(Exception.MEMBER_NOT_FOUND_EXCEPTION.getMessage()));

        return user;
    }
}
