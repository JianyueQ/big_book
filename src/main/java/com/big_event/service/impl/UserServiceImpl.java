package com.big_event.service.impl;

import com.big_event.mapper.UserMapper;
import com.big_event.pojo.User;
import com.big_event.service.UserService;
import com.big_event.utils.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Override
    public User findByUserName(String username) {
        User user = userMapper.findByUserName(username);
        return user;
    }

    @Override
    public void register(String username, String password) {
        //加密
        String md5String = Md5Util.getMD5String(password);
        //添加
        userMapper.add(username,md5String);
    }

    @Override
    public User findByUSerNameAndPassword(String username, String password) {
        //加密
        String md5String = Md5Util.getMD5String(password);
        //查询
        User user = userMapper.findByUSerNameAndPassword(username,md5String);
        return user;
    }

}
