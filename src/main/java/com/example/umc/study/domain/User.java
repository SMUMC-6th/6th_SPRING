package com.example.umc.study.domain;

import com.example.umc.study.domain.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access =AccessLevel.PROTECTED)
@Builder
@Getter

public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "user_id")
    private Long id;
    private String name;

    @OneToMany(mappedBy = "user")
    private List<Post> posts = new ArrayList<>();
}
