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
    private String authorName;

    @ElementCollection
    private List<String> supplementTags;

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "communityPost")
    private List<Comment> comments;


}