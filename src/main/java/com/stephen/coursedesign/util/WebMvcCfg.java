package com.stephen.coursedesign.util;

import com.stephen.coursedesign.util.login.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @program: course-design
 * @author: Stephen·Wang
 * @date: 2021/4/19 1:43
 * @Version:
 * @Description:
 */
@Configuration
public class WebMvcCfg implements WebMvcConfigurer {
    public final static String SESSION_KEY = "user";

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/emp/**","/main.html").excludePathPatterns("/index.html");
    }
}
