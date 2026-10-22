package com.hospital.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ChangePasswordDto {
    @NotBlank(message = "旧密码不能为空")
    private String oldPassword;

    @Size(min = 8, max = 64,message = "新密码必须在8-64长度之间")
    @NotBlank(message = "新密码不能为空")
    private String newPassword;

    @NotBlank(message = "确认密码不能为空")
    private String confirmPassword;
}
