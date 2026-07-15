package com.hospital.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/info")
    public String getInfo(@RequestParam Integer id,
                          @RequestParam String name){
        return "用户编号:" + id + "用户名:" + name;
    }
}
