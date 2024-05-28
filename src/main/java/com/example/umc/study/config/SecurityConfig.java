package com.example.umc.study.config;

import com.example.umc.study.config.filter.jwt.filter.JWTExceptionFilter;
import com.example.umc.study.config.filter.jwt.error.JwtAccessDeniedHandler;
import com.example.umc.study.config.filter.jwt.error.JwtAuthenticationEntryPoint;
import com.example.umc.study.config.filter.jwt.filter.LoginFilter;
import com.example.umc.study.config.filter.jwt.filter.JWTFilter;
import com.example.umc.study.config.filter.jwt.JWTUtil;
import lombok.RequiredArgsConstructor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import org.springframework.web.cors.CorsConfiguration;

import java.util.Collections;



@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final AuthenticationConfiguration authenticationConfiguration;
    private final JWTUtil jwtUtil;
    private final PrincipalDetailsService principalDetailsService;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    private final String[] allowUrl = {
            "/swagger-ui/**",
            "/swagger-resources/**",
            "/v3/api-docs/**",
            "/api/v1/posts/**",
            "/api/v1/replies/**",
            "/login"
    };

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.cors((c)->c.configurationSource(request -> {
            CorsConfiguration config = new CorsConfiguration();
            config.setAllowedOrigins(Collections.singletonList("*"));
            config.setAllowedMethods(Collections.singletonList("*"));
            config.setAllowCredentials(true);
            config.setAllowedHeaders(Collections.singletonList("*"));
            config.setMaxAge(3600L);
            return config;
        }));

        http.csrf(AbstractHttpConfigurer::disable);

        http.authorizeHttpRequests((requests) -> requests
                .requestMatchers(HttpMethod.POST,"/api/v1/users").permitAll()
                .requestMatchers(HttpMethod.POST,"/api/v1/users/{userId}/posts").hasAnyRole("USER", "ADMIN")
                .requestMatchers(HttpMethod.POST,"/api/v1/replies").hasRole( "ADMIN")
                .requestMatchers(allowUrl).permitAll()
                .anyRequest().authenticated());

        http.exceptionHandling(
                (configurer ->
                        configurer
                                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                                .accessDeniedHandler(jwtAccessDeniedHandler)
                )
        );

//        http.addFilterAfter(new TestFilter(), AnonymousAuthenticationFilter.class);


        http.addFilterAt(new LoginFilter(authenticationManager(authenticationConfiguration), jwtUtil), UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(new JWTFilter(jwtUtil, principalDetailsService), UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(new JWTExceptionFilter(), JWTFilter.class);

        http.formLogin(AbstractHttpConfigurer::disable);
        http.httpBasic(AbstractHttpConfigurer::disable);

        http
                .sessionManagement((session) -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
