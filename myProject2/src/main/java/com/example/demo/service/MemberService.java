package com.example.demo.service;

import com.example.demo.domain.enums.Gender;
import com.example.demo.domain.user.User;
import com.example.demo.dto.users.ProfileEditFormRequest;
import com.example.demo.exception.Exception;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.JwtTokenProvider;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final UserRepository userRepository;

    @Transactional
    public void changeProfile(Long id, ProfileEditFormRequest request) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(Exception.MEMBER_NOT_FOUND_EXCEPTION.getMessage()));

        user.setName(request.getName());
        user.setBirthDate(request.getBirthDate());
        user.setGender(Gender.valueOf(request.getGender()));
        user.setNickname(request.getNickname());
        user.setProfileImage(request.getProfileImage());

        userRepository.save(user);
    }
}
