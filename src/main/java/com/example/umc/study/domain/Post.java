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
public class Post extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    private String title;

    private String content;

    //post는 일대다 중 다 쪽이기에 Manytoont을 넣어줘야하고, 일대다일때 보통 연관관계의 주인이자 외래키는 다쪽에 있기 때문에 Joincolumn을 붙어준다.
    //fetch는 연관관계를 가져올때 어떻게 가져올지 결정하는 것. fetch 타입에는 LAZY와 EAGER전략이있는데 eager은 즉시로딩해서 가져오는것이고,
    //lazy는 지연로딩이며 proxy로 가져와서 get을 사용하면 바로 쓸수있는 형태로 만들어서 가져오는것(보통은 lazy가 더 좋음)

    //XToMany는 기본값이 Lazy 전략이고 XToOne은 기본값이 eager 전략이다.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    //댓글 일대다
    @OneToMany(mappedBy = "post")
    private List<Reply> replies = new ArrayList<>();


}
