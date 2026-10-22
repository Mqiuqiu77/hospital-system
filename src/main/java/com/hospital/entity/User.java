package com.hospital.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;


@TableName("users")
@Data
public class User {

    @TableId
    private Long id;

    private String username;

    private String password;

    @TableField("token_version")
    private Integer tokenVersion;

    private String role;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

}