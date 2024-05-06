package com.example.umc.study.domain;

import com.example.umc.study.domain.common.BaseEntity;
import com.example.umc.study.domain.mapping.PostCategory;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
public class Post extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    private String title;

    private String content;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "post")
    private List<Reply> replies = new ArrayList<>();

    @OneToMany(mappedBy = "post")
    private List<PostCategory> postCategories = new ArrayList<>();

    public void updatePost(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void setUser(User user) {
        this.user = user;
    }
}