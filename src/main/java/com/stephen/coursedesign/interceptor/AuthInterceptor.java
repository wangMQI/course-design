package com.stephen.coursedesign.interceptor;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.stephen.coursedesign.util.annotation.VerifyToken;

import com.stephen.coursedesign.entity.User;
import com.stephen.coursedesign.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @program: course-design
 * @author: Stephen·Wang
 * @date: 2021/4/22 14:32
 * @Version:
 * @Description:
 */
@Slf4j
public class AuthInterceptor implements HandlerInterceptor {
    @Autowired
    UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest,
                             HttpServletResponse httpServletResponse,
                             Object object) throws Exception {

        //从http请求头中去除token
        String token = httpServletRequest.getHeader("Authorization");
        //如果不是映射到controller方法直接放行
        if(!(object instanceof HandlerMethod)){
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) object;
        Method method = handlerMethod.getMethod();
        //检查需不需要验证token
        if(method.isAnnotationPresent(VerifyToken.class)){
            VerifyToken userLoginToken = method.getAnnotation(VerifyToken.class);
            if(userLoginToken.required()) {
                if (token == null) {
                    throw new RuntimeException("该请求没有token，请先获得token");
                }
                //获取token中的userPhone
                String userPhone;
                try{
                    userPhone = JWT.decode(token).getAudience().get(0);
                }catch (JWTDecodeException e) {
                    throw new RuntimeException("token非法，没有userPhone！");
                }
                User user = userService.findUserByUserPhone(userPhone);
                if (user == null){
                    throw new RuntimeException("用户不存在，请重新登录！");
                }
                //验证token
                JWTVerifier jwtVerifier =
                        JWT.require(Algorithm.HMAC256(user.getPassword())).build();
                try {
                    jwtVerifier.verify(token);
                }catch ( JWTVerificationException e) {
                    throw new RuntimeException("校验token发生异常！");
                }
                return true;

            }
        }

        return true;
    }


    @Override
    public void postHandle(HttpServletRequest httpServletRequest,
                           HttpServletResponse httpServletResponse,
                           Object o,
                           ModelAndView moduleAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest,
                           HttpServletResponse httpServletResponse,
                           Object o,
                           Exception e) throws Exception {

    }

}
