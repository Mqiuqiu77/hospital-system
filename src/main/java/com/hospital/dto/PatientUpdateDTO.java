package com.hospital.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PatientUpdateDTO {
    @NotBlank(message = "姓名不能为空")
    @Size(max = 50, message = "姓名不能超过50个字符")
    private String name;

    @NotBlank(message = "性别不能为空")
    @Size(max = 10, message = "性别不能超过10个字符")
    private String gender;

    @NotNull(message = "出生日期不能为空")
    @PastOrPresent(message = "出生日期不能晚于今天")
    private LocalDate birthDate;

    @NotBlank(message = "联系电话不能为空")
    @Size(max = 30, message = "联系电话不能超过30个字符")
    private String phone;

    @NotBlank(message = "地址不能为空")
    @Size(max = 255, message = "地址不能超过255个字符")
    private String address;
}
