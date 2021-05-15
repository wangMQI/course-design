package com.stephen.coursedesign.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.alibaba.druid.wall.WallConfig;
import com.alibaba.druid.wall.WallFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.sql.DataSource;

/**
 * @program: course-design
 * @author: Stephen·Wang
 * @date: 2021/4/27 18:30
 * @Version:
 * @Description:
 */
@Configuration
public class DruidConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource druidDataSource() {
        return new DruidDataSource();
    }

    /**
     * 配置监控服务器
     * @return 返回监控注册的servlet对象
     * @author SimpleWu
     */
    @Bean
    public ServletRegistrationBean statViewServlet() {
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
        //ServletRegistrationBean reg = new ServletRegistrationBean();
        //reg.setServlet(new StatViewServlet());
        //reg.addUrlMappings("/druid/*");
        // 添加IP白名单
        servletRegistrationBean.addInitParameter("allow", "127.0.0.1");
        // 添加IP黑名单，当白名单和黑名单重复时，黑名单优先级更高
        //servletRegistrationBean.addInitParameter("deny", "127.0.0.1");
        // 添加控制台管理用户
        servletRegistrationBean.addInitParameter("loginUsername", "admin");
        servletRegistrationBean.addInitParameter("loginPassword", "123456");
        // 是否能够重置数据
        servletRegistrationBean.addInitParameter("resetEnable", "false");
        return servletRegistrationBean;
    }

    @Bean
    public WallFilter wallFilter(){

        WallFilter wallFilter=new WallFilter();
        wallFilter.setConfig(wallConfig());
        return wallFilter;
    }

    @Bean
    public WallConfig wallConfig(){
        WallConfig config =new WallConfig();
        config.setMultiStatementAllow(true);//允许一次执行多条语句
        config.setNoneBaseStatementAllow(true);//允许非基本语句的其他语句
        return config;

    }

    /**
     * 配置服务过滤器
     */
    //@Bean
    //public FilterRegistrationBean statFilter() {
    //    FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter());
    //    // 添加过滤规则
    //    filterRegistrationBean.addUrlPatterns("/*");
    //    // 忽略过滤格式
    //    filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*,");
    //    return filterRegistrationBean;
    //}

}
