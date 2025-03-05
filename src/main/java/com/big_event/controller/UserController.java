package com.big_event.controller;


import com.big_event.pojo.Result;
import com.big_event.pojo.User;
import com.big_event.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    //查询用户是否已经注册
    @PostMapping("/register")
    public Result register(String username,String password){
        User user = userService.findByUserName(username);
        //查询用户是否已经注册
        if (user == null){
            //用户没有注册
            userService.register(username,password);
            return Result.success();
        } else {
            return Result.error("该用户名已经被注册");
        }
    }

    @PostMapping("/login")
    public Result login(String username,String password){
        //登录
        User user = userService.findByUSerNameAndPassword(username,password);
        if (user != null){
            //登录
            System.out.println();
            return Result.success();
        }else {
            return Result.error("账号或密码错误");
        }
    }



}
