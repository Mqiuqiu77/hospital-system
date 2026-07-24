package com.hospital.mapper;


import com.hospital.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/** User database access mapper. */

public interface UserMapper {

    User selectByUsername(@Param("username") String username);
}
