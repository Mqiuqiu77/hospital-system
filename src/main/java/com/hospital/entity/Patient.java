package com.hospital.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@TableName("patients")
public class Patient {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String patientNo;
    private String name;
    private String gender;
    private LocalDate birthDate;
    private String phone;
    private String address;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
