package com.hospital.service.impl;

import com.hospital.dto.ChangePasswordDto;
import com.hospital.dto.LoginDto;
import com.hospital.dto.RegisterDto;
import com.hospital.entity.User;
import com.hospital.exception.BusinessException;
import com.hospital.mapper.UserMapper;
import com.hospital.service.UserService;
import com.hospital.utils.JwtUtil;
import com.hospital.vo.LoginVO;
import com.hospital.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    private final PasswordEncoder passwordEncoder;


    private final UserMapper userMapper;
    private final JwtUtil jwtUtil;

    public UserServiceImpl(UserMapper userMapper,
                           JwtUtil jwtUtil,PasswordEncoder passwordEncoder) {
        this.userMapper = userMapper;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public LoginVO login(LoginDto dto) {

        log.info("用户{}开始登录", dto.getUsername());
        UserVO userVO = new UserVO();
        User user = userMapper.selectByUsername(dto.getUsername());
        if (user == null){
            throw new BusinessException("用户名不存在");
        }

        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            throw new BusinessException("密码错误");
        }

        BeanUtils.copyProperties(user,userVO);

        String token = jwtUtil.generateToken(user.getId(),user.getUsername(),user.getRole(),user.getTokenVersion());

        LoginVO loginVO = new LoginVO();
        loginVO.setUser(userVO);
        loginVO.setToken(token);
        return loginVO;
    }

    @Override
    public UserVO register(RegisterDto dto) {

        User existUser = userMapper.selectByUsername(dto.getUsername());
        if(existUser != null){
            throw new BusinessException("用户名已存在");
        }

        if(!dto.getConfirmPassword().equals(dto.getPassword())){
            throw new BusinessException("确认密码和密码不一致");
        }

        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setRole("USER");

        LocalDateTime now = LocalDateTime.now();
        user.setCreateTime(now);
        user.setUpdateTime(now);

        userMapper.insert(user);

        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(user,userVO);
        return userVO;
    }

    @Override
    public void changePassword(Long userId, ChangePasswordDto dto) {
        User user = userMapper.selectById(userId);
        if(user == null){
            throw new BusinessException("用户不存在");
        }

        if (!passwordEncoder.matches(dto.getOldPassword(), user.getPassword())){
            throw new BusinessException("旧密码不正确");

        }

        if(!dto.getConfirmPassword().equals(dto.getNewPassword())){
            throw new BusinessException("确认密码与新密码不一致");
        }

        LocalDateTime now = LocalDateTime.now();

        userMapper.updatePasswordById(userId,passwordEncoder.encode(dto.getNewPassword()),now);

    }
}
