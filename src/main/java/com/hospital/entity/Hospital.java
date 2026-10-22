package com.hospital.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("hospitals")
public class Hospital {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String name;

    private String address;

    private String phone;

    private String hospitalLevel;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

}
