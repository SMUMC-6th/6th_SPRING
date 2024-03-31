package com.example.umc.study.domain;

import com.example.umc.study.domain.mapping.PostCategory;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.boot.SpringApplication;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long id;

    private String title;

    @OneToMany(mappedBy = "category")
    private List<PostCategory> postCategories = new ArrayList<>();
}
