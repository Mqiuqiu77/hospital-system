package com.hospital.config;

import tools.jackson.databind.ObjectMapper;
import com.hospital.common.Result;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

public class SecurityConfig {
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

    @Bean
    UserDetailsService userDetailsService() {
        return username -> { throw new UsernameNotFoundException("User authentication is handled by UserService"); };
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http, ObjectMapper objectMapper) throws Exception {
        CookieCsrfTokenRepository csrf = CookieCsrfTokenRepository.withHttpOnlyFalse();
        return http
                .csrf(configurer -> configurer
                        .csrfTokenRepository(csrf)
                        .ignoringRequestMatchers("/user/login"))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/user/login", "/user/test", "/error").permitAll()
                        .anyRequest().authenticated())
                .formLogin(form -> form.disable())
                .httpBasic(basic -> basic.disable())
                .logout(logout -> logout
                        .logoutUrl("/user/logout")
                        .logoutSuccessHandler((request, response, authentication) ->
                                writeJson(response, objectMapper, HttpServletResponse.SC_OK,
                                        Result.success(null))))
                .exceptionHandling(exceptions -> exceptions
                        .authenticationEntryPoint((request, response, exception) ->
                                writeJson(response, objectMapper, HttpServletResponse.SC_UNAUTHORIZED,
                                        Result.error("请先登录")))
                        .accessDeniedHandler((request, response, exception) ->
                                writeJson(response, objectMapper, HttpServletResponse.SC_FORBIDDEN,
                                        Result.error("无权访问或CSRF令牌无效"))))
                .build();
    }

    private static void writeJson(HttpServletResponse response, ObjectMapper mapper,
                                  int status, Result<?> body) throws java.io.IOException {
        response.setStatus(status);
        response.setCharacterEncoding("UTF-8");
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        mapper.writeValue(response.getWriter(), body);
    }
}
