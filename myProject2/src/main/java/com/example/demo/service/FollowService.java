package com.example.demo.service;

import com.example.demo.domain.follow.Follow;
import com.example.demo.domain.user.Pharmacist;
import com.example.demo.domain.user.User;
import com.example.demo.dto.follow.FollowDto;
import com.example.demo.exception.Exception;
import com.example.demo.repository.FollowRepository;
import com.example.demo.repository.PharmacistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FollowService {

    private final FollowRepository followRepository;
    private final PharmacistRepository pharmacistRepository;

    public void follow(User user, Long pharmacistId) {
        Pharmacist pharmacist = pharmacistRepository.findById(pharmacistId)
                .orElseThrow(() -> new IllegalArgumentException(Exception.NOT_EXIST_PHARMACIST.getMessage()));

        Follow follow = new Follow(
                user,
                pharmacist
        );

        followRepository.save(follow);
    }

    public List<FollowDto> getMyFollows(Long id) {
        List<Follow> follows = followRepository.findAllByUserId(id);

        return follows.stream()
                .map(FollowDto::from)
                .toList();
    }

    public void deleteFollow(User user, Long pharmacistId) {
        Follow follow = followRepository.findByUserIdAndPharmacistId(user.getId(), pharmacistId)
                .orElseThrow(() -> new IllegalArgumentException(Exception.NOT_EXIST_FOLLOW.getMessage()));

        followRepository.delete(follow);
    }
}
