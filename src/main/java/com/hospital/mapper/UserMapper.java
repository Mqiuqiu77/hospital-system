package com.hospital.mapper;


import com.hospital.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/** User database access mapper. */

public interface UserMapper {

//    根据用户名查询
    User selectByUsername(@Param("username") String username);

//    查询全部用户
    List<User> selectAll();

//    根据id查询
    User selectById(@Param("id") Long id);

//    新增用户
    void insert(User user);

//    修改用户
    void update(User user);

//    删除用户

    void deleteById(@Param("id") Long id);

}
