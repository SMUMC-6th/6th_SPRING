package com.example.umc.study.domain.common;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass  //상위에서 밑으로 필드만 줄때
@EntityListeners(AuditingEntityListener.class)  //변경감지
@Getter
public class BaseEntity {
    // 만들어질때 수정될때 시간이 바뀌는 것
    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;
}
