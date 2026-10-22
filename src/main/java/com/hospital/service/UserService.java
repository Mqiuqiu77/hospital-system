package com.hospital.service;

import com.hospital.dto.ChangePasswordDto;
import com.hospital.dto.LoginDto;
import com.hospital.dto.RegisterDto;
import com.hospital.entity.User;
import com.hospital.vo.LoginVO;
import com.hospital.vo.UserVO;
import org.springframework.stereotype.Service;


public interface UserService {
    LoginVO login(LoginDto dto);

    UserVO register(RegisterDto dto);

    void changePassword(Long userId, ChangePasswordDto dto);
}

