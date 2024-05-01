package com.example.umc.study.dto;

import com.example.umc.study.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class UserResponseDTO {
    @Getter //필드에 접근
    @AllArgsConstructor //모든 필드를 인자로 받는 생성자를 자동으로 생성
    @NoArgsConstructor //매개변수가 없는 기본 생성자를 자동으로 생성
    @Builder //빌더 패턴을 쉽게 적용
    public static class JoinResultDTO {
        private Long userId;

        private LocalDateTime createAt; //생성시간
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class UserPreviewDTO {
        private Long userId;
        private String name;
        private LocalDateTime updateAt;
        private LocalDateTime createAt;
    }

    public static UserResponseDTO.UserPreviewDTO toUserPreviewDTO(User user) {
        return UserPreviewDTO.builder()
                .userId(user.getId())
                .name(user.getName())
                .updateAt(user.getUpdatedAt())
                .createAt(user.getCreatedAt())
                .build();
    }
}
