package com.stephen.coursedesign.config;

import com.stephen.coursedesign.interceptor.AuthInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @program: course-design
 * @author: Stephen·Wang
 * @date: 2021/4/22
 * @Version:
 * @Description:
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(authInterceptor())
                .addPathPatterns("/**"); //拦截所有请求
    }

    @Bean
    public AuthInterceptor authInterceptor(){
        return new AuthInterceptor();
    }

}
