package com.big_event.service;

import com.big_event.pojo.User;

public interface UserService {
    //根据用户名查询用户
    User findByUserName(String username);
    //注册
    void register(String username, String password);
    //
    void update(User user);

    void updateAvatar(String avatarUrl);

    void updatePwd(String rePwd, String username);
}
