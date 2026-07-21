package com.hospital.mapper;


import com.hospital.entiy.User;
import org.apache.ibatis.annotations.Mapper;

//数据库访问类
@Mapper
public interface UserMapper {
    User findByUsername(String username);
}
