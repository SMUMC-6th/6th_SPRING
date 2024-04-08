package com.example.umc.study.domain.common;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;


//BaseEntity.java는 일반적으로 JPA에서 엔티티 클래스들의 공통 속성 및 동작을 정의하기 위해 사용
//이 클래스는 일반적으로 모든 엔티티 클래스의 상위 클래스로 사용되며, 이를 통해 코드의 재사용성과 유지 보수성을 높일 수 있다.

@MappedSuperclass //MappedSuperClass는 상위에서 밑으로 필드만 줄 때
// 엔티티 클래스가 해당 클래스를 상속받을 때 부모 클래스의 매핑 정보를 상속받도록 지정
@EntityListeners(AuditingEntityListener.class) //EntityLister는 변경될 때 감지 되는 것
@Getter
public class BaseEntity {
    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;
}