package com.example.umc.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing //JPA 엔티티의 생성일자 및 수정일자를 자동으로 추적하고 관리할 수 있도록 설정
public class UmcStudyApplication {

	public static void main(String[] args) {
		SpringApplication.run(UmcStudyApplication.class, args);
	}

}
