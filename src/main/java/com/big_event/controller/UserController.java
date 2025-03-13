package com.big_event.controller;


import com.big_event.pojo.Result;
import com.big_event.pojo.User;
import com.big_event.service.UserService;
import com.big_event.utils.JwtUtil;
import com.big_event.utils.Md5Util;
import com.big_event.utils.ThreadLocalUtil;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/user")
@Validated
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    //查询用户是否已经注册
    @PostMapping("/register")
    public Result register(@Pattern(regexp = "^\\S{5,16}$") String username,@Pattern(regexp = "^\\S{5,16}$") String password){
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
    public Result<String> loginUser(@Pattern(regexp = "^\\S{5,16}$") String username,@Pattern(regexp = "^\\S{5,16}$") String password){
        User loginUser = userService.findByUserName(username);
        //查询是否有这个用户名
        if (loginUser == null){
            return Result.error("用户名错误");
        }
        //将密码加密之后在对比数据库中存放的密文
        if (Md5Util.getMD5String(password).equals(loginUser.getPassword())){
            Map<String,Object> claims = new HashMap<>();
            claims.put("id",loginUser.getId());
            claims.put("username",loginUser.getUsername());
            String token = JwtUtil.genToken(claims);
            //把token存储在Redis中
            ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
            operations.set(token,token, 1,TimeUnit.HOURS);
            //登录成功
            return Result.success(token);
        }
        return Result.error("密码错误");
    }

    @GetMapping("/userInfo")
    //获取用户详细信息
    public Result<User> userInfo(/*@RequestHeader("Authorization") String token*/){
//        Map<String, Object> map = JwtUtil.parseToken(token);
        Map<String,Object> map = ThreadLocalUtil.get();
        String username = (String) map.get("username");
        User user = userService.findByUserName(username);
        return Result.success(user);
    }

    @PutMapping("/update")
    public Result userUpdate(@RequestBody @Validated User user){
        //更新已登录用户基本信息
        userService.update(user);
        return Result.success();
    }

    @PatchMapping("/updateAvatar")
    public Result<String> updateAvatar(@RequestParam @URL String avatarUrl){
        //更新已登录用户的头像
        userService.updateAvatar(avatarUrl);
        return Result.success();
    }

    @PatchMapping("/updatePwd")
    public Result updatePwd(@RequestBody Map<String,String> params ,@RequestHeader("Authorization") String token){
        String oldPwd = params.get("old_pwd");
        String newPwd = params.get("new_pwd");
        String rePwd = params.get("re_pwd");
        //检验非空
        if ( !StringUtils.hasLength(oldPwd) || !StringUtils.hasLength(newPwd) || !StringUtils.hasLength(rePwd)){
            return Result.error("缺少必要参数");
        }
        //检验原来密码是否正确
        Map<String,Object> map = ThreadLocalUtil.get();
        String username = (String) map.get("username");
        User userLogin = userService.findByUserName(username);
        if ( !userLogin.getPassword().equals(Md5Util.getMD5String(oldPwd)) ){
            return Result.error("旧密码不一致");
        }
        //检验新密码与确认密码是否相同
        if ( !newPwd.equals(rePwd) ){
            return Result.error("新密码与确认密码不一致");
        }
        //检验旧密码是否与新密码相同
        if ( userLogin.getPassword().equals(Md5Util.getMD5String(newPwd)) ){
            return Result.error("新密码与旧密码一致");
        }
        //调用service
        userService.updatePwd(rePwd,username);
        //修改密码的同时，删除Token
        ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
        operations.getOperations().delete(token);
        return  Result.success();
    }
}
