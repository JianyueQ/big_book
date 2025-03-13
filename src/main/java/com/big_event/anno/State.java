package com.big_event.anno;

import com.big_event.Validation.StateValidation;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented //元注解
@Target({FIELD})    //元注解
@Retention(RUNTIME) //元注解
@Constraint(
        validatedBy = {StateValidation.class}    //提供指定校验规则的类
)
public @interface State {
    //提供校验失败后的提示信息
    String message() default "state的参数值只能是已发布或者草稿";
    //指定分组
    Class<?>[] groups() default {};
    //负载    获取State注解的附加信息
    Class<? extends Payload>[] payload() default {};
}
