package com.sdcuike.practice.web2;

import javax.servlet.Filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sdcuike.spring.extend.web.MvcConfigurerAdapter;

/**
 * web 组件配置
 * 
 * @author sdcuike
 *         <p>
 *         Created on 2017-02-10
 *         <p>
 *         自定义注入，并支持依赖注入，组件排序
 */
@Configuration
public class WebComponent2Config extends MvcConfigurerAdapter {

    @Bean
    public FilterRegistrationBean filterDemo3Registration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(filterDemo3());
        registration.addUrlPatterns("/*");
        registration.addInitParameter("paramName", "paramValue");
        registration.setName("filterDemo3");
        registration.setOrder(6);
        return registration;
    }

    @Bean
    public FilterRegistrationBean filterDemo4Registration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(filterDemo4());
        registration.addUrlPatterns("/*");
        registration.addInitParameter("paramName", "paramValue");
        registration.setName("filterDemo4");
        registration.setOrder(7);
        return registration;
    }

    @Bean
    public Filter filterDemo3() {
        return new FilterDemo3();
    }

    @Bean
    public Filter filterDemo4() {
        return new FilterDemo4();
    }

}
