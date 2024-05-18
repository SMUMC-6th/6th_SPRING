package com.example.umc.study.domain;

import com.example.umc.study.domain.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
public class User extends BaseEntity {
    //user의 기본 키를 넣어주기
    //id 어노테이션과 generated value 어노테이션을 붙어주는데 generation type 전략에는 여러가지가 있음
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    private String password;

    private String name;

    private String email;

    private String role;


    //user과 post는 erd상에서 일대다로 매핑되어있다.
    //user은 일대다중 일쪽이기때문에 onetomany를 넣어주고 post와 매핑이 되어있는 곳에 mappedby=user을 붙어주어야함
    //user쪽이기에 해야하는것, user하나당 post가 여러개 이기에 LIst로 해줘야함)
    @OneToMany(mappedBy = "user")
    private List<Post> posts = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Reply> replies = new ArrayList<>();

    public void update(String name) {
        this.name = name;
    }
}
