package com.sdcuike.practice.web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import org.springframework.core.Ordered;

import lombok.extern.slf4j.Slf4j;

@WebFilter("/*")
@Slf4j
public class FilterDemo6 implements Filter, Ordered {
    private final int order = 55;

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

    @Override
    public int getOrder() {
        return order;
    }

}
