package com.hospital.service;

import com.hospital.dto.LoginDto;
import com.hospital.entity.User;
import com.hospital.vo.UserVO;
import org.springframework.stereotype.Service;


public interface UserService {
    UserVO login(LoginDto dto);
}
