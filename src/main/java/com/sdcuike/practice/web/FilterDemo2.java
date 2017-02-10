package com.sdcuike.practice.web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;

@WebFilter("/*")
@Order(1)
public class FilterDemo2 implements Filter {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Override
    public void destroy() {
        log.info("" + getClass() + " destroy");
    }

    @Override
    public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2) throws IOException, ServletException {
        log.info("" + getClass() + " doFilter ");
        arg2.doFilter(arg0, arg1);
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
        log.info("" + getClass() + " init");

    }

}
