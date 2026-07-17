package com.hospital.service;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final String username;
    private final String encodedPassword;
    private final PasswordEncoder passwordEncoder;
    private final int maxAttempts;
    private final Duration lockDuration;
    private final ConcurrentHashMap<String, LoginAttempt> attempts = new ConcurrentHashMap<>();

    public UserService(@Value("${app.security.username}") String username,
                       @Value("${app.security.password}") String password,
                       @Value("${app.security.max-login-attempts:5}") int maxAttempts,
                       @Value("${app.security.lock-duration:15m}") Duration lockDuration,
                       PasswordEncoder passwordEncoder) {
        this.username = username;
        this.passwordEncoder = passwordEncoder;
        this.encodedPassword = passwordEncoder.encode(password);
        this.maxAttempts = maxAttempts;
        this.lockDuration = lockDuration;
    }

    public void authenticate(String inputUsername, String password) {
        String normalizedUsername = inputUsername.trim().toLowerCase(Locale.ROOT);
        LoginAttempt attempt = attempts.get(normalizedUsername);
        Instant now = Instant.now();
        if (attempt != null && attempt.lockedUntil() != null && now.isBefore(attempt.lockedUntil())) {
            throw new AccountLockedException("登录失败次数过多，请稍后再试");
        }
        boolean passwordCorrect = passwordEncoder.matches(password, encodedPassword);
        boolean usernameCorrect = username.equalsIgnoreCase(normalizedUsername);
        if (!usernameCorrect || !passwordCorrect) {
            recordFailure(normalizedUsername, now);
            throw new BadCredentialsException("用户名或密码错误");
        }
        attempts.remove(normalizedUsername);
    }

    private void recordFailure(String username, Instant now) {
        attempts.compute(username, (key, old) -> {
            int failures = old == null || (old.lockedUntil() != null && !now.isBefore(old.lockedUntil()))
                    ? 1 : old.failures() + 1;
            return new LoginAttempt(failures, failures >= maxAttempts ? now.plus(lockDuration) : null);
        });
    }

    private record LoginAttempt(int failures, Instant lockedUntil) {}

    public static class AccountLockedException extends RuntimeException {
        public AccountLockedException(String message) { super(message); }
    }
}
