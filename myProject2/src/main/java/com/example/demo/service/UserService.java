package com.example.demo.service;

import com.example.demo.domain.user.User;
import com.example.demo.dto.users.ProfileEditFormRequest;
import com.example.demo.dto.users.UserDto;
import com.example.demo.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserDto getUserProfile(User user) {

        return new UserDto(user);
    }

    @Transactional
    public void changeProfile(User user, ProfileEditFormRequest request) {

        user.editInfo(request);

        userRepository.save(user);
    }
}
