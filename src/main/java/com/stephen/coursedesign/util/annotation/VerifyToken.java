package com.stephen.coursedesign.util.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @program: course-design
 * @author: Stephen·Wang
 * @date: 2021/4/22 0:28
 * @Version:
 * @Description:加到controller方法上表示该方法需要验证token
 */
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface VerifyToken {
    boolean required() default true;
}
