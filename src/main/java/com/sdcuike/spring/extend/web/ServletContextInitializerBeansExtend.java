package com.sdcuike.spring.extend.web;

import javax.servlet.Filter;

import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.annotation.Order;

/**
 * @author sdcuike
 *         <p>
 *         Created on 2017.02.13
 *         <p>
 */
public class ServletContextInitializerBeansExtend extends ServletContextInitializerBeansModify {

    public ServletContextInitializerBeansExtend(ListableBeanFactory beanFactory) {
        super(beanFactory);
    }

    @Override
    protected void addServletContextInitializerBean(Class<?> type, String beanName, ServletContextInitializer initializer, ListableBeanFactory beanFactory, Object source) {
        Order order = AnnotationUtils.findAnnotation(source.getClass(), Order.class);
        if (Filter.class == type && order != null) {
            FilterRegistrationBean filterRegistrationBean = (FilterRegistrationBean) initializer;
            filterRegistrationBean.setOrder(order.value());
        }

        super.addServletContextInitializerBean(type, beanName, initializer, beanFactory, source);

    }

}
