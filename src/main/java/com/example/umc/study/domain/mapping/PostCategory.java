package com.example.umc.study.domain.mapping;

import com.example.umc.study.domain.Category;
import com.example.umc.study.domain.Post;
import com.example.umc.study.domain.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED) //protect 레벨로 생성자를 붙이는 것
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder //Builder 패턴 사용 가능
@Getter
public class PostCategory extends BaseEntity {
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
