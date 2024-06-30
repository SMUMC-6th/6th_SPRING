package com.example.umc.study.config;

//import com.example.umc.study.config.filter.TestFilter;
//import com.example.umc.study.config.filter.TestFilter2;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
//
//@Configuration
//@EnableWebSecurity(debug = true)
//public class SecurityConfig {
//
//    private final String[] allowUrl = {
//            "/swagger-ui/**",
//            "/swagger-resources/**",
//            "/v3/api-docs/**",
//            "/api/v1/posts/**",
//            "/api/v1/replies/**",
//            "/test"
//    };
//
//
////    @Bean
////    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
////
////        http.authorizeHttpRequests((requests) -> requests
////                .requestMatchers("/swagger-ui/**").permitAll()
////                .anyRequest().authenticated());
////        return http.build();
////    }
//
//    @Bean
//    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
//        http.csrf(AbstractHttpConfigurer::disable);
//        http.authorizeHttpRequests((requests) -> requests
//
////                .requestMatchers("/api/v1/replies/**").permitAll()  //api/v1/replies/**은 /api/v1/replies가 앞으로 붙어있는 모든 주소에 대해 허용
////                .requestMatchers("api/v1/users/**").hasAnyAuthority("admin")
////                .requestMatchers("/api/v1/posts/**").permitAll()//api/v1/users/**은 /api/v1/users가 앞으로 붙어있는 모든 주소에 대해 admin 역할을 가진 user에게 허용
//                .requestMatchers(HttpMethod.POST,"/api/v1/users").permitAll()
////                .requestMatchers(HttpMethod.POST,"/api/v1/users/{userId}/posts").hasAnyRole("USER", "ADMIN")
////                .requestMatchers(HttpMethod.POST,"/api/v1/replies").hasRole("ADMIN")
//                .requestMatchers(HttpMethod.POST, "/api/v1/users/{userId}/posts").hasAnyRole("ADMIN", "USER")
//                .requestMatchers(HttpMethod.PATCH, "/api/v1/posts/{postId}").hasAnyRole("ADMIN","USER")
//                .requestMatchers(HttpMethod.POST, "/users/{userId}/posts/{postId}/replies/").hasRole("ADMIN")
//                .requestMatchers(allowUrl).permitAll()
//                .anyRequest().authenticated()); // anyRequest.authenticated는 나머지 모든 request에 대해 인증
//        http.formLogin(AbstractHttpConfigurer::disable);
//        http.httpBasic(AbstractHttpConfigurer::disable);
//        http.
//                sessionManagement((session) -> session
//                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS));
//        //http.addFilterAfter(new TestFilter(), BasicAuthenticationFilter.class);
//        http.addFilterAfter(new TestFilter2(), BasicAuthenticationFilter.class);
//        return http.build();
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
////    @Bean
////    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
////        http.csrf(AbstractHttpConfigurer::disable);
////        http.authorizeHttpRequests((requests) -> requests
////                .anyRequest().authenticated()
////        );
////        return http.build();
////    }
//
//
//}


import com.example.umc.study.config.filter.TestFilter;
import com.example.umc.study.config.filter.TestFilter2;
import com.example.umc.study.config.filter.*;
import com.example.umc.study.config.jwt.JWTUtil;
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

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity(debug = true)
@RequiredArgsConstructor
public class SecurityConfig {

    private final AuthenticationConfiguration authenticationConfiguration;
    private final JWTUtil jwtUtil;
    private final PrincipalDetailService principalDetailsService;
    private final JWTAccessDeniedHandler jwtAccessDeniedHandler;
    private final JWTAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    private final String[] allowUrl = {
            "/swagger-ui/**",
            "/swagger-resources/**",
            "/v3/api-docs/**",
            "/api/v1/posts/**",
            "api/v1/replies/**"
    };
    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);
        http.authorizeHttpRequests((requests) -> requests
                .requestMatchers(HttpMethod.POST, "/api/v1/users").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/v1/users/{userId}/posts").hasAnyRole("USER", "ADMIN")
                .requestMatchers(HttpMethod.PATCH, "/api/v1//posts/{postId}").hasAnyRole("USER", "ADMIN")
                .requestMatchers(HttpMethod.POST, "/api/v1/users/{userId}/posts/{postId}/replies").hasAnyRole("ADMIN")
                .requestMatchers(HttpMethod.PATCH, "/api/v1/replies/{replyId}").hasAnyRole("ADMIN")
                .requestMatchers(allowUrl).permitAll()
                .anyRequest().authenticated());
        /*http.addFilterAfter(new TestFilter(), BasicAuthenticationFilter.class);*/
        http.addFilterAfter(new TestFilter2(), AnonymousAuthenticationFilter.class);
        http.formLogin(withDefaults());
        http.httpBasic(withDefaults());
        /*http.addFilterAfter(new TestFilter2(), AnonymousAuthenticationFilter.class);*/
        http.exceptionHandling(
                (configurer ->
                        configurer
                                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                                .accessDeniedHandler(jwtAccessDeniedHandler)
                )
        );

        http.addFilterAt(new LoginFilter(authenticationManager(authenticationConfiguration), jwtUtil),
                UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(new JWTFilter(jwtUtil, principalDetailsService),
                UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(new JWTExceptionFilter(), JWTFilter.class);
        // form 로그인 방식 disable
        http.formLogin(AbstractHttpConfigurer::disable);
        // http basic 인증 방식 disable
        http.httpBasic(AbstractHttpConfigurer::disable);
        // Session Stateless하게 관리
        http.sessionManagement((session) -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        );
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}