package com.example.demo.domain.community;
import com.example.demo.domain.BaseTimeEntity;
import com.example.demo.domain.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class Comment extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private CommunityPost communityPost;

    public Comment() {
    }

    public Comment(String content, User user, CommunityPost communityPost) {
        this.content = content;
        this.user = user;
        this.communityPost = communityPost;
    }
}