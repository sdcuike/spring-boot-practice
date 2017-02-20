package com.sdcuike.spring.extend.web;

import javax.servlet.Filter;

import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.OrderUtils;

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
        Integer order = OrderUtils.getOrder(source.getClass());
        if (order == null && source instanceof Ordered) {
            order = ((Ordered) source).getOrder();
        }

        if (Filter.class == type && order != null) {
            FilterRegistrationBean filterRegistrationBean = (FilterRegistrationBean) initializer;
            filterRegistrationBean.setOrder(order);
        }

        super.addServletContextInitializerBean(type, beanName, initializer, beanFactory, source);

    }

}
