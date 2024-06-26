package com.example.umc.study.config;

import com.example.umc.study.config.filter.TestFilter;
import com.example.umc.study.config.filter.TestFilter2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity(debug = true)
public class SecurityConfig {

    private final String[] allowUrl = {
            "/swagger-ui/**",
            "/swagger-resources/**",
            "/v3/api-docs/**",
            "/api/v1/posts/**",
            "/api/v1/replies/**",
            "/test"
    };


//    @Bean
//    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//
//        http.authorizeHttpRequests((requests) -> requests
//                .requestMatchers("/swagger-ui/**").permitAll()
//                .anyRequest().authenticated());
//        return http.build();
//    }

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);
        http.authorizeHttpRequests((requests) -> requests

//                .requestMatchers("/api/v1/replies/**").permitAll()  //api/v1/replies/**은 /api/v1/replies가 앞으로 붙어있는 모든 주소에 대해 허용
//                .requestMatchers("api/v1/users/**").hasAnyAuthority("admin")
//                .requestMatchers("/api/v1/posts/**").permitAll()//api/v1/users/**은 /api/v1/users가 앞으로 붙어있는 모든 주소에 대해 admin 역할을 가진 user에게 허용
                .requestMatchers(HttpMethod.POST,"/api/v1/users").permitAll()
//                .requestMatchers(HttpMethod.POST,"/api/v1/users/{userId}/posts").hasAnyRole("USER", "ADMIN")
//                .requestMatchers(HttpMethod.POST,"/api/v1/replies").hasRole("ADMIN")
                .requestMatchers(HttpMethod.POST, "/api/v1/users/{userId}/posts").hasAnyRole("ADMIN", "USER")
                .requestMatchers(HttpMethod.PATCH, "/api/v1/posts/{postId}").hasAnyRole("ADMIN","USER")
                .requestMatchers(HttpMethod.POST, "/users/{userId}/posts/{postId}/replies/").hasRole("ADMIN")
                .requestMatchers(allowUrl).permitAll()
                .anyRequest().authenticated()); // anyRequest.authenticated는 나머지 모든 request에 대해 인증
        http.formLogin(Customizer.withDefaults());
        http.httpBasic(Customizer.withDefaults());
        //http.addFilterAfter(new TestFilter(), BasicAuthenticationFilter.class);
        http.addFilterAfter(new TestFilter2(), BasicAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http.csrf(AbstractHttpConfigurer::disable);
//        http.authorizeHttpRequests((requests) -> requests
//                .anyRequest().authenticated()
//        );
//        return http.build();
//    }


}
