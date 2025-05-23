package com.example.demo.service;

import com.example.demo.domain.community.CommunityPost;
import com.example.demo.domain.enums.PostSortStatus;
import com.example.demo.domain.user.User;
import com.example.demo.dto.community.CommunityPostDto;
import com.example.demo.dto.community.CommunityRequest;
import com.example.demo.exception.Exception;
import com.example.demo.repository.CommunityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommunityService {

    private final CommunityRepository communityRepository;

    public Long createPost(CommunityRequest request, User user) {
        CommunityPost post = new CommunityPost(
                request.getTitle(),
                request.getContent(),
                user
        );

        return communityRepository.save(post).getId();
    }

    public List<CommunityPostDto> getAllPosts(PostSortStatus sort) {
        List<CommunityPost> posts = new ArrayList<>();

        if (sort.equals(PostSortStatus.RECENT)) {
            posts = communityRepository.findAllByOrderByCreatedAtDesc();
        } else if (sort.equals(PostSortStatus.TITLE)) {
            posts = communityRepository.findAllByOrderByTitleAsc();
        } else if (sort.equals(PostSortStatus.POPULAR)) {
            posts = communityRepository.findAllByOrderByViewCountDesc();
        }

        return posts
                .stream()
                .map(CommunityPostDto::from)
                .toList();
    }

    @Transactional
    public CommunityPostDto getPostById(Long id) {
        CommunityPost post = communityRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(Exception.NOT_EXIST_POST.getMessage()));
        post.increaseViewCount();

        return CommunityPostDto.from(post);
    }

    @Transactional
    public void updatePost(Long id, CommunityRequest request, User user) {
        CommunityPost post = communityRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(Exception.NOT_EXIST_POST.getMessage()));

        if (!post.getUser().getId().equals(user.getId())) {
            throw new IllegalStateException(Exception.ONLY_EDIT_POST_BY_WRITER.getMessage());
        }

        post.setTitle(request.getTitle());
        post.setContent(request.getContent());

        communityRepository.save(post);
    }

    @Transactional
    public void deletePost(Long id, User user) {
        CommunityPost post = communityRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(Exception.NOT_EXIST_POST.getMessage()));

        if (!post.getUser().getId().equals(user.getId())) {
            throw new IllegalStateException(Exception.ONLY_EDIT_POST_BY_WRITER.getMessage());
        }

        communityRepository.deleteById(id);
    }
}
