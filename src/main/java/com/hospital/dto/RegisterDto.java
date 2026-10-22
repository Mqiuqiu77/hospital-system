package com.hospital.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterDto {
    @NotBlank(message = "用户名不能为空")
    @Size(max = 50,message = "用户名不能超过50个字符")
    private String username;

    @NotBlank(message = "密码不能为空")
    @Size(min = 8,max = 64,message = "密码需要在8-64个字符之间")
    private String password;

    @NotBlank(message = "确认密码不能为空")
    private String confirmPassword;
}
