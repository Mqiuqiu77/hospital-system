package com.hospital.controller;

import com.hospital.common.Result;
import com.hospital.dto.ChangePasswordDto;
import com.hospital.dto.LoginDto;
import com.hospital.dto.RegisterDto;
import com.hospital.security.LoginUser;
import com.hospital.service.UserService;
import com.hospital.vo.CurrentUserVO;
import com.hospital.vo.LoginVO;
import com.hospital.vo.UserVO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public Result<LoginVO> login(@Valid @RequestBody LoginDto dto){
        return Result.success(userService.login(dto));
    }

    @PostMapping("/register")
    public Result<UserVO> register(@Valid @RequestBody RegisterDto dto){
        return Result.success(userService.register(dto));
    }

    @PutMapping("/password")
    public Result<Void> changePassword(
            @AuthenticationPrincipal LoginUser loginUser,
            @Valid @RequestBody ChangePasswordDto dto
    ) {
        userService.changePassword(loginUser.getUserId(), dto);
        return Result.success(null);
    }

    @GetMapping("/test")
    public String test(){
        System.out.println(userService.getClass());
        return "ok";
    }

    @GetMapping("/me")
    public Result<CurrentUserVO> me(@AuthenticationPrincipal LoginUser loginUser){
        return Result.success(new CurrentUserVO(loginUser.getUserId(),
                loginUser.getUsername()));

    }



}
