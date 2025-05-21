package com.example.demo.repository;

import com.example.demo.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByName(String name);

    Optional<User> findById(Long id);

    Optional<User> findByEmail(String email);
}
