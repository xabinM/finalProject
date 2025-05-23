package com.example.demo.repository;

import com.example.demo.domain.follow.Follow;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FollowRepository extends JpaRepository<Follow, Long> {
    List<Follow> findAllByUserId(Long id);

    Optional<Follow> findByUserIdAndPharmacistId(Long id, Long pharmacistId);
}
