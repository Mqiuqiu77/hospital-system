package com.hospital.service.impl;

import com.hospital.dto.LoginDto;
import com.hospital.entity.User;
import com.hospital.exception.BusinessException;
import com.hospital.mapper.UserMapper;
import com.hospital.service.UserService;
import com.hospital.vo.UserVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
   @Autowired
    private UserMapper userMapper;

    @Override
    public UserVO login(LoginDto dto) {
        UserVO userVO = new UserVO();
        User user = userMapper.selectByUsername(dto.getUsername());
        if (user == null){
            throw new BusinessException("用户名不存在");
        }
        BeanUtils.copyProperties(user,userVO);
        return userVO;
    }
}
