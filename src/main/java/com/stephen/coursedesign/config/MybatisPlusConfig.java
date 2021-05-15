/*
package com.stephen.coursedesign.config;

import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Properties;

*/
/**
 * @program: course-design
 * @author: Stephen·Wang
 * @date: 2021/5/15 21:30
 * @Version:
 * @Description:性能分析插件
 *//*


@EnableTransactionManagement
@Configuration
@MapperScan("com.stephen.entity.mapper")
public class  MybatisPlusConfig {

    */
/**
     * SQL执行效率插件
     *//*

    @Bean
    public PerformanceInterceptor performanceInterceptor() {
        return new PerformanceInterceptor();
    }
    */
/**
     * 分页插件
     *//*

    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PerformanceInterceptor performanceInterceptor =new PerformanceInterceptor();

        //格式化sql语句

        Properties properties =new Properties();

        properties.setProperty("format", "false");

        performanceInterceptor.setProperties(properties);
        return new PaginationInterceptor();
    }

    @Bean
    public ISqlInjector sqlInjector() {
        return new LogicSqlInjector();
    }


}
*/
