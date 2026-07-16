package com.hospital.controller;

import com.hospital.dto.LoginDTO;
import com.hospital.dto.UserInfoResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/info")
    public UserInfoResponse getInfo(@RequestParam Integer id,
                                    @RequestParam String name) {
        return new UserInfoResponse(id, name);
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginDTO logindto){
        return "欢迎:" + logindto.getUsername();
    }
}
