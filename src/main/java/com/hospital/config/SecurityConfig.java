package com.hospital.config;

import com.hospital.filter.JwtAuthenticationFilter;
import com.hospital.mapper.UserMapper;
import com.hospital.security.RestAccessDeniedHandler;
import com.hospital.security.RestAuthenticationEntryPoint;
import com.hospital.utils.JwtUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.http.HttpMethod;

@Configuration
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http, JwtUtil jwtUtil, UserMapper userMapper,
            RestAuthenticationEntryPoint authenticationEntryPoint,
            RestAccessDeniedHandler accessDeniedHandler,
            StringRedisTemplate stringRedisTemplate) throws Exception {

        JwtAuthenticationFilter jwtAuthenticationFilter =
                new JwtAuthenticationFilter(jwtUtil,userMapper,stringRedisTemplate);

        http
                .addFilterBefore(
                        jwtAuthenticationFilter,
                        UsernamePasswordAuthenticationFilter.class
                ).exceptionHandling(exceptions -> exceptions
                        .authenticationEntryPoint(authenticationEntryPoint)
                        .accessDeniedHandler(accessDeniedHandler))
                .csrf(csrf -> csrf.disable())
                .formLogin(form -> form.disable())
                .httpBasic(basic -> basic.disable())
                .sessionManagement(session -> session
                        .sessionCreationPolicy(
                                SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(
                                "/user/login",
                                "/hello",
                                "/error",
                                "/user/register"
                        ).permitAll()
                        .requestMatchers(HttpMethod.PUT, "/patients/**")
                        .hasRole("ADMIN")
                        .anyRequest().authenticated());



        return http.build();
    }
}