package com.big_event.mapper;

import com.big_event.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper {
    //根据用户名查询用户信息
    @Select("select * from user where username = #{username}")
    User findByUserName(String username);
    //添加
    @Insert("insert into user (username, password,create_time,update_time)" + " values (#{username}, #{password},now(),now())")
    void add(String username, String password);
    //修改用户昵称和电子邮箱，更新时间
    @Update("update user set nickname = #{nickname},email = #{email},update_time = #{updateTime} where id = #{id} ")
    void update(User user);

    @Update("update user set user_pic = #{avatarUrl}, update_time = now() where id = #{id}")
    void updateAvatar(String avatarUrl, Integer id);

    @Update("update user set password = #{md5String} where username = #{username}")
    void updatePwd(String md5String, String username);
}
