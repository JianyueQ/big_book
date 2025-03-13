package com.big_event.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDateTime;

/*
* lombok  在编译阶段，为实体类自动生成setter,getter,toString
* pom引入依赖
* */
@Data
public class User {
    @NotNull
    private Integer id;//主键ID
    private String username;//用户名

    //让springMVC把当前对象转换成字符串的时候,忽略password.
    @JsonIgnore
    private String password;//密码

    @NotNull
    @Pattern(regexp = "^\\S{1,10}$")
    private String nickname;//昵称

    @NotNull
    @Email
    private String email;//邮箱


    private String userPic;//用户头像地址
    private LocalDateTime createTime;//创建时间
    private LocalDateTime updateTime;//更新时间
}
