package com.hospital.controller;

import com.hospital.common.Result;
import com.hospital.dto.LoginDto;
import com.hospital.entity.User;
import com.hospital.service.UserService;
import com.hospital.vo.UserVO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public Result<UserVO> login(@Valid @RequestBody LoginDto dto){
        return Result.success(userService.login(dto));
    }


    @GetMapping("/test")
    public String test(){
        System.out.println(userService.getClass());
        return "ok";
    }

}
