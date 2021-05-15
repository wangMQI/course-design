package com.stephen.coursedesign.util.login;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @program: course-design
 * @author: Stephen·Wang
 * @date: 2021/4/19 1:41
 * @Version:
 * @Description:
 */
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {

        Object user = request.getSession().getAttribute("user");
        if (user == null || user.equals(""))  {
            response.sendRedirect("/");
            return false;
        }
        return true;
    }
}
