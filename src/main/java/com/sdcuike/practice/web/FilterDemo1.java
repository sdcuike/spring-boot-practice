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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sdcuike.practice.config.CommonConfig;

@WebFilter("/*")
public class FilterDemo1 implements Filter {
    private final Logger log = LoggerFactory.getLogger(getClass());

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

}
