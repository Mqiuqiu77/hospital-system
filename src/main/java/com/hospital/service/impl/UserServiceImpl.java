package com.hospital.service.impl;

import com.hospital.entity.User;
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
    public UserVO login(String username) {
        UserVO userVO = new UserVO();
        User user = userMapper.selectByUsername(username);
        BeanUtils.copyProperties(user,userVO);
        return userVO;
    }
}
