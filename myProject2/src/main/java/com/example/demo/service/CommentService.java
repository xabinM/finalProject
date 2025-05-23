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
    public void updatePost(Long commentId, CommentRequest request, User user) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 댓글입니다."));

        if (!comment.getUser().getId().equals(user.getId())) {
            throw new SecurityException("댓글 작성자만 수정 및 삭제할 수 있습니다.");
        }

        comment.setContent(request.getContent());

        commentRepository.save(comment);
    }

    @Transactional
    public void delete(User user, Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 댓글입니다."));

        if (!comment.getUser().getId().equals(user.getId())) {
            throw new SecurityException("댓글 작성자만 수정 및 삭제할 수 있습니다.");
        }

        commentRepository.delete(comment);
    }
}
