package com.hospital.mapper;


import com.hospital.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/** User database access mapper. */
@Mapper
public interface UserMapper {

    User findByUsername(@Param("username") String username);
}
