package com.example.demo.service;

import com.example.demo.domain.community.Comment;
import com.example.demo.domain.community.CommunityPost;
import com.example.demo.domain.user.User;
import com.example.demo.dto.comment.CommentDto;
import com.example.demo.dto.comment.CommentRequest;
import com.example.demo.exception.Exception;
import com.example.demo.repository.CommentRepository;
import com.example.demo.repository.CommunityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final CommunityRepository communityRepository;


    public Long saveComment(Long postId, User user, CommentRequest request) {
        CommunityPost post = communityRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException(Exception.NOT_EXIST_POST.getMessage()));

        Comment comment = new Comment(
                request.getContent(),
                user,
                post
        );

        return commentRepository.save(comment).getId();
    }

    public List<CommentDto> getComments(Long postId) {
        CommunityPost post = communityRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException(Exception.NOT_EXIST_POST.getMessage()));

        return post.getComments().stream()
                .map(CommentDto::from)
                .toList();
    }

    @Transactional
    public void updateComment(Long commentId, CommentRequest request, User user) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException(Exception.NOT_EXIST_COMMENT.getMessage()));

        if (!comment.getUser().getId().equals(user.getId())) {
            throw new SecurityException(Exception.ONLY_EDIT_COMMENT_BY_WRITER.getMessage());
        }

        comment.setContent(request.getContent());

        commentRepository.save(comment);
    }

    @Transactional
    public void deleteComment(User user, Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException(Exception.NOT_EXIST_COMMENT.getMessage()));

        if (!comment.getUser().getId().equals(user.getId())) {
            throw new SecurityException(Exception.ONLY_EDIT_COMMENT_BY_WRITER.getMessage());
        }

        commentRepository.delete(comment);
    }
}
