package com.example.umc.study.config;

import com.example.umc.study.config.Filter.TestFilter;
import com.example.umc.study.config.Filter.TestFilter2;
import com.example.umc.study.config.jwt.*;
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
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import java.util.Collections;

import static org.springframework.security.config.Customizer.withDefaults;


@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final AuthenticationConfiguration authenticationConfiguration;
    private final JWTUtil jwtUtil;
    private final PrincipalDetailsService principalDetailsService;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;


    private final String[] allowUrl = {
            "/swagger-ui/**",
            "/swagger-resource/**",
            "/v3/api-docs/**",
            "/api/v1/posts/**",
            "/api/v1/replies/**",
            "/login"
    };

    @Bean
    public AuthenticationManager authenticationManager  (AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
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
                .requestMatchers(HttpMethod.POST, "/api/v1/users").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/v1/users/{userId}/posts").hasAnyRole("USER", "ADMIN")
                .requestMatchers(HttpMethod.PATCH, "/api/v1/users/posts/{postId}").hasAnyRole("USER", "ADMIN")
                .requestMatchers(HttpMethod.POST, "/api/v1/replies").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PATCH, "/api/v1/replies/{replyId}").hasRole("ADMIN")
                .requestMatchers(allowUrl).permitAll()
                .anyRequest().authenticated()
        );

        http.sessionManagement((session) -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        );

        http.exceptionHandling(
                (configurer ->
                        configurer
                                .accessDeniedHandler(jwtAccessDeniedHandler)
                )
        );

        http.formLogin(AbstractHttpConfigurer::disable);
        http.httpBasic(AbstractHttpConfigurer::disable);
        // http.addFilterAfter(new TestFilter(), BasicAuthenticationFilter.class);
        // http.addFilterAfter(new TestFilter2(), AnonymousAuthenticationFilter.class);
        http.addFilterBefore(new JWTFilter(jwtUtil, principalDetailsService), UsernamePasswordAuthenticationFilter.class);
        http.addFilterAt(new LoginFilter(authenticationManager(authenticationConfiguration), jwtUtil), UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(new JWTExceptionFilter(), JWTFilter.class);
        return http.build();

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
