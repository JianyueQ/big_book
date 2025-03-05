package com.big_event.service;

import com.big_event.pojo.User;

public interface UserService {
    //根据用户名查询用户
    User findByUserName(String username);
    //注册
    void register(String username, String password);
    //查询用户名和密码
    User findByUSerNameAndPassword(String username, String password);

}
