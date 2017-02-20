package com.sdcuike.practice.web;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import org.springframework.core.Ordered;

import com.sdcuike.practice.config.CommonConfig;

import lombok.extern.slf4j.Slf4j;

@WebFilter("/*")
@Slf4j
public class FilterDemo5 implements Filter, Ordered {
    private final int    order = 66;

    @Resource
    private CommonConfig commonConfig;

    @Override
    public void destroy() {
        log.info("" + getClass() + " destroy");

    }

    @Override
    public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2) throws IOException, ServletException {
        log.info("" + getClass() + " doFilter " + commonConfig);
        arg2.doFilter(arg0, arg1);

    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
        log.info("" + getClass() + " init");

    }

    @Override
    public int getOrder() {
        return order;
    }

}
