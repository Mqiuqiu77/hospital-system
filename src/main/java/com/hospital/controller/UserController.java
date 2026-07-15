package com.hospital.controller;

import com.hospital.dto.UserInfoResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/info")
    public UserInfoResponse getInfo(@RequestParam Integer id,
                                    @RequestParam String name) {
        return new UserInfoResponse(id, name);
    }
}
