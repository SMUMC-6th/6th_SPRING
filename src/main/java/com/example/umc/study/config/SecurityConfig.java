package com.example.umc.study.config;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.springframework.security.config.Customizer.withDefaults;

public class SecurityConfig {

    private final String[] swaggerUrls = {"/swagger-ui/**", "/swagger-resources/**", "/v3/**"};
    private final String[] allowUrls = {
            "/api/v1/posts/**",
            "/api/v1/replies/**"
    };

    // 허용 Urls
    private final String[] allowedUrls = Stream.concat(Arrays.stream(swaggerUrls), Arrays.stream(allowUrls))
            .toArray(String[]::new);

    // PasswordEncoerder Been 등록
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        // cors 비활성화
        http.cors(cors -> cors
                .configurationSource(CorsConfig.apiConfigurationSource()));

        // form 로그인 방식 disable
        http.formLogin(AbstractHttpConfigurer::disable);

        // http basic 인증 방식 disable
        http.httpBasic(AbstractHttpConfigurer::disable);

        // 경로별 인가
        http.authorizeHttpRequests(authorizeRequests -> authorizeRequests
                                .requestMatchers("/api/v1/users/**").permitAll()
                                .requestMatchers(allowedUrls).permitAll()
                                .anyRequest().authenticated()
                );

        return http.build();
    }




}
