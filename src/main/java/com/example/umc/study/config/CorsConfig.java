package com.example.umc.study.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.List;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Bean
    public static CorsConfigurationSource apiConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        // 응답 허용할 ip
        List<String> allowedOriginPatterns = Arrays.asList("*");
        configuration.setAllowedOriginPatterns(allowedOriginPatterns);

        // 응답 허용할 HTTP Method
        List<String> allowedHttpMethods = Arrays.asList("GET", "POST", "PUT", "DELETE");
        configuration.setAllowedMethods(allowedHttpMethods);

        // 응답 허용할 header
        List<String> allowedHeaders = Arrays.asList("*");
        configuration.setAllowedHeaders(allowedHeaders);

        // 내 서버가 응답을 할 때 응답해준 json을 자바스크립트에서 처리할 수 있게 할지를 설정
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        // /api/** 로 들어오는 모든 요청들은 config를 따르도록 등록!
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

}
