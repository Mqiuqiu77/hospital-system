package com.hospital.security;


import lombok.Getter;

@Getter
public class LoginUser {
    private final Long userId;
    private final String username;
    private final String role;

    public LoginUser(Long userId, String username,String role) {
        this.userId = userId;
        this.username = username;
        this.role = role;
    }
}
