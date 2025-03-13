package com.big_event.interceptor;

import com.big_event.pojo.Result;
import com.big_event.utils.JwtUtil;
import com.big_event.utils.ThreadLocalUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;

@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //令牌验证
        String token = request.getHeader("Authorization");
        //验证token
        try {
            //从Redis中获取相同的token
            ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
            String redisToken = operations.get(token);
            if (redisToken == null){
                throw new RuntimeException();
            }
            Map<String, Object> claims = JwtUtil.parseToken(token);
            //用ThreadLocalUtil将业务数据存入
            ThreadLocalUtil.set(claims);
            //放行
            return true;
        } catch (Exception e) {
            response.setStatus(401);
            //拦截
            return false;
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //请求完成后清除业务数据
        ThreadLocalUtil.remove();
    }
}
