package com.hospital.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class HospitalUpdateDTO {
    @NotBlank(message = "医院名称不能为空")
    @Size(max = 100,message = "医院名称不能超过100个字符")
    private  String name;

    @NotBlank(message = "地址不能为空")
    @Size(max = 255,message = "地址不能超过255个字符")
    private String address;

    @NotBlank(message = "手机号码不能为空")
    @Size(max = 30,message = "手机号码不能超过30个字符")
    private String phone;

    @NotBlank(message = "医院等级不能为空")
    @Size(max = 50,message = "医院等级不能超过50个字符")
    private  String hospitalLevel;
}
