package com.sdcuike.spring.extend.web;

import java.util.Collection;

import org.springframework.boot.context.embedded.EmbeddedWebApplicationContext;
import org.springframework.boot.web.servlet.ServletContextInitializer;

/**
 * 自定义EmbeddedWebApplicationContext，主要为了支持@WebFilter 支持 @Order 排序
 * 
 * @author sdcuike
 *         <p>
 *         Created on 2017.02.13
 *         <p>
 */
public class EmbeddedWebApplicationContextExtend extends EmbeddedWebApplicationContext {
    @Override
    protected Collection<ServletContextInitializer> getServletContextInitializerBeans() {
        return new ServletContextInitializerBeansExtend(getBeanFactory());
    }
}
