package com.hospital.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import javax.smartcardio.ATR;

@Data
public class LoginDto {
    @NotBlank(message = "用户名不能为空")
    private String username;

    @NotBlank(message = "密码不能为空")
    private String password;
}
