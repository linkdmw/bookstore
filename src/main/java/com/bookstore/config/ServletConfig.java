package com.bookstore.config;

import com.bookstore.utils.CheckImageServlet;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by IDEA.
 *
 * @Author: 霍梦威
 * @Date: 2020/6/11 14:09
 */
@SpringBootConfiguration
public class ServletConfig {
    //注册setvlet
    @Bean
    public ServletRegistrationBean createBookServlet(){
        ServletRegistrationBean servlet = new ServletRegistrationBean(new CheckImageServlet(),"/imageCode");
        return servlet;
    }
}
