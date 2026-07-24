package com.hospital.controller;

import com.hospital.common.Result;
import com.hospital.entity.User;
import com.hospital.service.UserService;
import com.hospital.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public Result<UserVO> login(@RequestParam String username){
        return Result.success(userService.login(username));
    }

}
