package com.big_event.mapper;

import com.big_event.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    //查询用户名
    @Select("select username from user where username = #{username}")
    User findByUserName(String username);
    //添加
    @Insert("insert into user (username, password,create_time,update_time)" +" values (#{username}, #{password},now(),now())")
    void add(String username, String password);
    //查询用户名和密码
    @Select("select user.username,user.password from user where username = #{username} and password = #{password}")
    User findByUSerNameAndPassword(String username, String password);

}
