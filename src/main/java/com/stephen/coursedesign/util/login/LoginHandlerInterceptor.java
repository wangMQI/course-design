package com.stephen.coursedesign.util.login;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @program: course-design
 * @author: Stephen·Wang
 * @date: 2021/4/18 13:10
 * @Version:
 * @Description:
 */
public class LoginHandlerInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //用户登录成功后,应该有自己的session
        Object session = request.getSession().getAttribute("username");
        if (session == null) {
            request.setAttribute("msg", "权限不够,请先登录");
            request.getRequestDispatcher("/login.html").forward(request, response);
            return false;
        } else {
            return true;
        }
    }
}
