package com.example.umc.study.domain;

import com.example.umc.study.domain.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED) //protect 레벨로 생성자를 붙이는 것
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder //Builder 패턴 사용 가능
@Getter
public class Reply extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //기본기를 어떻게 생성할 것인지에 대한 전략
    @Column(name = "reply_id")
    private Long id;

    private String title;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY) //fetch란 연관관계 가져올 때 어떻게 가져올지 정하는 것
    //Fetch 타입 1. eager 전략: 즉시 로딩해서 같이 가져오는 것
    //Fetch 타입 2. Lazy 전략: 지연로딩, proxy로 가져와서 get을 쓰면 바로 쓸 수 있는 형태로 만들어서 가져오는 것
    //보통은 LAZY로 정해주는 것이 좋음
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY) //fetch란 연관관계 가져올 때 어떻게 가져올지 정하는 것
    //Fetch 타입 1. eager 전략: 즉시 로딩해서 같이 가져오는 것
    //Fetch 타입 2. Lazy 전략: 지연로딩, proxy로 가져와서 get을 쓰면 바로 쓸 수 있는 형태로 만들어서 가져오는 것
    //보통은 LAZY로 정해주는 것이 좋음
    @JoinColumn(name = "post_id")
    private Post post;
}
