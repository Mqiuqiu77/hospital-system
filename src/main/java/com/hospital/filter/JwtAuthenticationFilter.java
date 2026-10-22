package com.hospital.filter;

import com.hospital.entity.User;
import com.hospital.mapper.UserMapper;
import com.hospital.security.LoginUser;
import com.hospital.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Objects;

public class JwtAuthenticationFilter extends OncePerRequestFilter {
   private final JwtUtil jwtUtil;

   private final UserMapper userMapper;

   private final StringRedisTemplate stringRedisTemplate;

   public JwtAuthenticationFilter(JwtUtil jwtUtil, UserMapper userMapper, StringRedisTemplate stringRedisTemplate){

       this.jwtUtil = jwtUtil;
       this.userMapper = userMapper;
       this.stringRedisTemplate = stringRedisTemplate;
   }

   private Integer getCurrentTokenVersion(Long userId){
       String key = "auth:token-version:" + userId;
       String cacheVersion = stringRedisTemplate.opsForValue().get(key);

       if (cacheVersion != null){
           return  Integer.valueOf(cacheVersion);
       }

       User user = userMapper.selectById(userId);
       if(user == null){
           return null;
       }

       Integer currentVersion = user.getTokenVersion();
       stringRedisTemplate.opsForValue().set(key,String.valueOf(currentVersion), Duration.ofMinutes(5));

       return currentVersion;

   }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorization =
                request.getHeader(HttpHeaders.AUTHORIZATION);
        if (!StringUtils.hasText(authorization)
                || !authorization.startsWith("Bearer ")) {

            filterChain.doFilter(request, response);
            return;
        }
        String token = authorization.substring(7).trim();

        if(!StringUtils.hasText(token)){
            filterChain.doFilter(request, response);
            return;
        }

        try {
            Claims claims = jwtUtil.parseToken(token);

            Long userId = jwtUtil.getUserId(claims);
            String username = jwtUtil.getUsername(claims);
            String role = jwtUtil.getRole(claims);

            Integer tokenVersion = jwtUtil.getTokenVersion(claims);

//            User user = userMapper.selectById(userId);
            Integer currentVersion = getCurrentTokenVersion(userId);

            if (currentVersion != null
                    && tokenVersion != null
                    && Objects.equals(currentVersion, tokenVersion)) {
                LoginUser loginUser = new LoginUser(userId, username, role);
                SimpleGrantedAuthority authority =
                        new SimpleGrantedAuthority("ROLE_" + role);

                UsernamePasswordAuthenticationToken authentication =
                        UsernamePasswordAuthenticationToken.authenticated(
                                loginUser, null, List.of(authority));
                SecurityContext context = SecurityContextHolder.createEmptyContext();
                context.setAuthentication(authentication);
                SecurityContextHolder.setContext(context);
            } else {
                SecurityContextHolder.clearContext();
            }
        }catch (JwtException | IllegalArgumentException e) {
            // Token 无效，保持匿名状态
            SecurityContextHolder.clearContext();
        }

            filterChain.doFilter(request, response);





    }
}
