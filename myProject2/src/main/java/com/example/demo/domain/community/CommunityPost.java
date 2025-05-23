package com.example.demo.domain.community;

import com.example.demo.domain.BaseTimeEntity;
import com.example.demo.domain.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class CommunityPost extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String content;
    private Integer viewCount = 0;

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "communityPost")
    private List<Comment> comments;

    public CommunityPost() {
    }

    public CommunityPost(String title, String content, User user) {
        this.title = title;
        this.content = content;
        this.user = user;
    }

    public void increaseViewCount() {
        this.viewCount += 1;
    }
}