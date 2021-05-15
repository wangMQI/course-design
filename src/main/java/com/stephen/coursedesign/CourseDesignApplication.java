package com.stephen.coursedesign;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@MapperScan("com.baomidou.mybatisplus.samples.quickstart.mapper")
@MapperScan("com.stephen.coursedesign.entity.mapper")
public class CourseDesignApplication {

    public static void main(String[] args) {
        SpringApplication.run(CourseDesignApplication.class, args);
    }

}
