package com.example.demo.repository;

import com.example.demo.domain.community.CommunityPost;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommunityRepository extends JpaRepository<CommunityPost, Long> {
    List<CommunityPost> findAllByOrderByCreatedAtDesc();

    List<CommunityPost> findAllByOrderByTitleAsc();

    List<CommunityPost> findAllByOrderByViewCountDesc();
}
