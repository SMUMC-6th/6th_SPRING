package com.example.umc.study.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED) //protect 레벨로 생성자를 붙이는 것
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder //Builder 패턴 사용 가능
public class PostCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //기본기를 어떻게 생성할 것인지에 대한 전략
    @Column(name = "post_category_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoty_id")
    private Category category;
}
