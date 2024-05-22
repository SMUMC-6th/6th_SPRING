package com.example.umc.study.domain;

import com.example.umc.study.domain.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED) //protect 레벨로 생성자를 붙이는 것
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder //Builder 패턴 사용 가능
@Getter
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //기본기를 어떻게 생성할 것인지에 대한 전략
    @Column(name = "user_id")
    private Long id;

    private String name;

    private String email;

    private String password;

    private String role;

    @OneToMany(mappedBy = "user")
    private List<Post> posts = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Reply> replies = new ArrayList<>();

    //update 기능에 사용할 로직
    public void update(String name) {
        this.name = name;
    }
}
