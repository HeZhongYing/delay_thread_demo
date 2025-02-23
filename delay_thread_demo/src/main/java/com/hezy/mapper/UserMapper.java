package com.hezy.mapper;

import com.hezy.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    @Insert("insert into user (id, username, password) values (#{id}, #{username}, #{password})")
    void insert(User user);
}
