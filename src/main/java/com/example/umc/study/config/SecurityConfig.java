package com.example.umc.study.config;

import com.example.umc.study.auth.filter.CustomDaoAuthenticationProvider;
import com.example.umc.study.auth.filter.JwtExceptionFilter;
import com.example.umc.study.auth.filter.JwtFilter;
import com.example.umc.study.auth.filter.LoginFilter;
import com.example.umc.study.auth.userdetails.PrincipalDetailsService;
import com.example.umc.study.auth.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Arrays;
import java.util.stream.Stream;

@Configuration
//@EnableWebSecurity(debug = true)
@RequiredArgsConstructor
public class SecurityConfig {

    private final AuthenticationConfiguration authenticationConfiguration;
    private final JwtUtil jwtUtil;
    private final PrincipalDetailsService principalDetailsService;

    private final String[] swaggerUrls = {"/swagger-ui/**", "/swagger-resources/**", "/v3/api-docs/**"};
    private final String[] allowUrls = {
            "/api/v1/posts/**",
            "/api/v1/replies/**",
            "/login"
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
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public CustomDaoAuthenticationProvider customDaoAuthenticationProvider() {
        CustomDaoAuthenticationProvider provider = new CustomDaoAuthenticationProvider();
        provider.setUserDetailsService(principalDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        // cors disable
        http.cors(cors -> cors
                .configurationSource(CorsConfig.apiConfigurationSource()));

        // csrf disable
        http.csrf(AbstractHttpConfigurer::disable);

        // form 로그인 방식 disable
        http.formLogin(AbstractHttpConfigurer::disable);

        // http basic 인증 방식 disable
        http.httpBasic(AbstractHttpConfigurer::disable);

        // Session Stateless하게 관리
        http.sessionManagement((session) -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        );

        // 경로별 인가
        http.authorizeHttpRequests(authorizeRequests -> authorizeRequests
                        .requestMatchers(HttpMethod.POST, "/api/v1/users").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/v1/users/{userId}/posts").hasAnyRole("USER", "ADMIN")
                        .requestMatchers(HttpMethod.PATCH, "/api/v1//posts/{postId}").hasAnyRole("USER", "ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/v1/users/{userId}/posts/{postId}/replies").hasAnyRole("ADMIN")
                        .requestMatchers(HttpMethod.PATCH, "/api/v1/replies/{replyId}").hasAnyRole("ADMIN")
                        .requestMatchers(allowedUrls).permitAll()
                        .requestMatchers(HttpMethod.POST, "api/v1/users").permitAll()
                                .anyRequest().authenticated()
                );

        http.addFilterAt(new LoginFilter(authenticationManager(authenticationConfiguration), jwtUtil),
                UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(new JwtFilter(jwtUtil, principalDetailsService),
                UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(new JwtExceptionFilter(), JwtFilter.class);

        return http.build();
    }

}
