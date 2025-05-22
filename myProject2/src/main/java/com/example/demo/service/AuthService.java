package com.example.demo.service;

import com.example.demo.domain.enums.UserRole;
import com.example.demo.domain.user.Pharmacist;
import com.example.demo.domain.user.User;
import com.example.demo.dto.auth.LoginMemberDto;
import com.example.demo.dto.auth.SignupRequest;
import com.example.demo.exception.Exception;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService implements UserDetailsService {

    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(Exception.MEMBER_NOT_FOUND_EXCEPTION.getMessage()));
    }

    public LoginMemberDto authenticate(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(Exception.MEMBER_NOT_FOUND_EXCEPTION.getMessage()));

        if (!user.getPassword().equals(password)) {
            throw new BadCredentialsException(Exception.PASSWORD_NOT_MATCH_EXCEPTION.getMessage());
        }

        return new LoginMemberDto(user);
    }

    public String getToken(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(Exception.MEMBER_NOT_FOUND_EXCEPTION.getMessage()));

        return jwtTokenProvider.generateToken(user.getEmail(), user.getId().toString());
    }

    public void signup(SignupRequest request) {
        Optional<User> existingMember = userRepository.findByName(request.getName());
        if (existingMember.isPresent()) {
            throw new IllegalArgumentException(Exception.SIGNUP_USERNAME_DUPLICATE_EXCEPTION.getMessage());
        }

        User user = new User(
                request.getName(),
                request.getPassword(),
                request.getEmail(),
                request.getBirthDate(),
                request.getGender(),
                request.getNickname(),
                request.getProfileImage(),
                request.getRole()
        );

        if (user.getRole() == UserRole.PHARMACIST) {
            Pharmacist pharmacist = new Pharmacist(
                    request.getLicenseNumber(),
                    request.getHospitalName(),
                    user
            );
            user.assignPharmacist(pharmacist);
        }

        userRepository.save(user);
    }
}
