package com.sdcuike.spring.extend.web;

import java.lang.reflect.Field;
import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.EventListener;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.Servlet;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.aop.scope.ScopedProxyUtils;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.boot.web.servlet.DelegatingFilterProxyRegistrationBean;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.RegistrationBean;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.boot.web.servlet.ServletContextInitializerBeans;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.core.annotation.AnnotationAwareOrderComparator;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

/**
 * copy自{@link ServletContextInitializerBeans},主要解决了反射解决spring的小bug及访问控制权限，以利于自定义扩展
 * 
 * @author sdcuike
 *         <p>
 *         Created on 2017.02.13
 *         <p>
 */
public class ServletContextInitializerBeansModify extends AbstractCollection<ServletContextInitializer> {

    private static final String                                        DISPATCHER_SERVLET_NAME = "dispatcherServlet";

    protected static final Log                                         logger                  = LogFactory
            .getLog(ServletContextInitializerBeansModify.class);

    /**
     * Seen bean instances or bean names.
     */
    protected final Set<Object>                                        seen                    = new HashSet<Object>();

    protected final MultiValueMap<Class<?>, ServletContextInitializer> initializers;

    private List<ServletContextInitializer>                            sortedList;

    public ServletContextInitializerBeansModify(ListableBeanFactory beanFactory) {
        this.initializers = new LinkedMultiValueMap<Class<?>, ServletContextInitializer>();
        addServletContextInitializerBeans(beanFactory);
        addAdaptableBeans(beanFactory);
        List<ServletContextInitializer> sortedInitializers = new ArrayList<ServletContextInitializer>();
        for (Map.Entry<?, List<ServletContextInitializer>> entry : this.initializers
                .entrySet()) {
            AnnotationAwareOrderComparator.sort(entry.getValue());
            sortedInitializers.addAll(entry.getValue());
        }
        this.sortedList = Collections.unmodifiableList(sortedInitializers);
    }

    private void addServletContextInitializerBeans(ListableBeanFactory beanFactory) {
        for (Entry<String, ServletContextInitializer> initializerBean : getOrderedBeansOfType(
                beanFactory, ServletContextInitializer.class)) {
            addServletContextInitializerBean(initializerBean.getKey(),
                    initializerBean.getValue(), beanFactory);
        }
    }

    private void addServletContextInitializerBean(String beanName,
                                                  ServletContextInitializer initializer, ListableBeanFactory beanFactory) {
        if (initializer instanceof ServletRegistrationBean) {
            Servlet source = getServlet((ServletRegistrationBean) initializer);
            addServletContextInitializerBean(Servlet.class, beanName, initializer,
                    beanFactory, source);
        } else if (initializer instanceof FilterRegistrationBean) {
            Filter source = ((FilterRegistrationBean) initializer).getFilter();
            addServletContextInitializerBean(Filter.class, beanName, initializer,
                    beanFactory, source);
        } else if (initializer instanceof DelegatingFilterProxyRegistrationBean) {
            String source = getTargetFilterName((DelegatingFilterProxyRegistrationBean) initializer);
            addServletContextInitializerBean(Filter.class, beanName, initializer,
                    beanFactory, source);
        } else if (initializer instanceof ServletListenerRegistrationBean) {
            EventListener source = ((ServletListenerRegistrationBean<?>) initializer)
                    .getListener();
            addServletContextInitializerBean(EventListener.class, beanName, initializer,
                    beanFactory, source);
        } else {
            addServletContextInitializerBean(ServletContextInitializer.class, beanName,
                    initializer, beanFactory, initializer);
        }
    }

    protected void addServletContextInitializerBean(Class<?> type, String beanName,
                                                    ServletContextInitializer initializer, ListableBeanFactory beanFactory,
                                                    Object source) {
        this.initializers.add(type, initializer);
        if (source != null) {
            // Mark the underlying source as seen in case it wraps an existing bean
            this.seen.add(source);
        }
        if (ServletContextInitializerBeansModify.logger.isDebugEnabled()) {
            String resourceDescription = getResourceDescription(beanName, beanFactory);
            int order = getOrder(initializer);
            ServletContextInitializerBeansModify.logger.debug("Added existing "
                    + type.getSimpleName() + " initializer bean '" + beanName
                    + "'; order=" + order + ", resource=" + resourceDescription);
        }
    }

    protected final String getResourceDescription(String beanName,
                                                  ListableBeanFactory beanFactory) {
        if (beanFactory instanceof BeanDefinitionRegistry) {
            BeanDefinitionRegistry registry = (BeanDefinitionRegistry) beanFactory;
            return registry.getBeanDefinition(beanName).getResourceDescription();
        }
        return "unknown";
    }

    @SuppressWarnings("unchecked")
    private void addAdaptableBeans(ListableBeanFactory beanFactory) {
        MultipartConfigElement multipartConfig = getMultipartConfig(beanFactory);
        addAsRegistrationBean(beanFactory, Servlet.class,
                new ServletRegistrationBeanAdapter(multipartConfig));
        addAsRegistrationBean(beanFactory, Filter.class,
                new FilterRegistrationBeanAdapter());
        for (Class<?> listenerType : ServletListenerRegistrationBean
                .getSupportedTypes()) {
            addAsRegistrationBean(beanFactory, EventListener.class,
                    (Class<EventListener>) listenerType,
                    new ServletListenerRegistrationBeanAdapter());
        }
    }

    private MultipartConfigElement getMultipartConfig(ListableBeanFactory beanFactory) {
        List<Entry<String, MultipartConfigElement>> beans = getOrderedBeansOfType(
                beanFactory, MultipartConfigElement.class);
        return (beans.isEmpty() ? null : beans.get(0).getValue());
    }

    private <T> void addAsRegistrationBean(ListableBeanFactory beanFactory, Class<T> type,
                                           RegistrationBeanAdapter<T> adapter) {
        addAsRegistrationBean(beanFactory, type, type, adapter);
    }

    private <T, B extends T> void addAsRegistrationBean(ListableBeanFactory beanFactory,
                                                        Class<T> type, Class<B> beanType, RegistrationBeanAdapter<T> adapter) {
        List<Map.Entry<String, B>> beans = getOrderedBeansOfType(beanFactory, beanType,
                this.seen);
        for (Entry<String, B> bean : beans) {
            if (this.seen.add(bean.getValue())) {
                int order = getOrder(bean.getValue());
                String beanName = bean.getKey();
                // One that we haven't already seen
                RegistrationBean registration = adapter.createRegistrationBean(beanName,
                        bean.getValue(), beans.size());
                registration.setName(beanName);
                registration.setOrder(order);
                this.initializers.add(type, registration);
                if (ServletContextInitializerBeansModify.logger.isDebugEnabled()) {
                    ServletContextInitializerBeansModify.logger.debug(
                            "Created " + type.getSimpleName() + " initializer for bean '"
                                    + beanName + "'; order=" + order + ", resource="
                                    + getResourceDescription(beanName, beanFactory));
                }
            }
        }
    }

    private final int getOrder(Object value) {
        return new AnnotationAwareOrderComparator() {
            @Override
            public int getOrder(Object obj) {
                return super.getOrder(obj);
            }
        }.getOrder(value);
    }

    private <T> List<Entry<String, T>> getOrderedBeansOfType(
                                                             ListableBeanFactory beanFactory, Class<T> type) {
        return getOrderedBeansOfType(beanFactory, type, Collections.emptySet());
    }

    private <T> List<Entry<String, T>> getOrderedBeansOfType(
                                                             ListableBeanFactory beanFactory, Class<T> type, Set<?> excludes) {
        List<Entry<String, T>> beans = new ArrayList<Entry<String, T>>();
        Comparator<Entry<String, T>> comparator = new Comparator<Entry<String, T>>() {

            @Override
            public int compare(Entry<String, T> o1, Entry<String, T> o2) {
                return AnnotationAwareOrderComparator.INSTANCE.compare(o1.getValue(),
                        o2.getValue());
            }

        };
        String[] names = beanFactory.getBeanNamesForType(type, true, false);
        Map<String, T> map = new LinkedHashMap<String, T>();
        for (String name : names) {
            if (!excludes.contains(name) && !ScopedProxyUtils.isScopedTarget(name)) {
                T bean = beanFactory.getBean(name, type);
                if (!excludes.contains(bean)) {
                    map.put(name, bean);
                }
            }
        }
        beans.addAll(map.entrySet());
        Collections.sort(beans, comparator);
        return beans;
    }

    @Override
    public Iterator<ServletContextInitializer> iterator() {
        return this.sortedList.iterator();
    }

    @Override
    public int size() {
        return this.sortedList.size();
    }

    /**
     * Adapter to convert a given Bean type into a {@link RegistrationBean} (and hence a {@link ServletContextInitializer}.
     */
    protected interface RegistrationBeanAdapter<T> {

        RegistrationBean createRegistrationBean(String name, T source,
                                                int totalNumberOfSourceBeans);

    }

    /**
     * {@link RegistrationBeanAdapter} for {@link Servlet} beans.
     */
    private static class ServletRegistrationBeanAdapter
            implements RegistrationBeanAdapter<Servlet> {

        private final MultipartConfigElement multipartConfig;

        ServletRegistrationBeanAdapter(MultipartConfigElement multipartConfig) {
            this.multipartConfig = multipartConfig;
        }

        @Override
        public RegistrationBean createRegistrationBean(String name, Servlet source,
                                                       int totalNumberOfSourceBeans) {
            String url = (totalNumberOfSourceBeans == 1 ? "/" : "/" + name + "/");
            if (name.equals(DISPATCHER_SERVLET_NAME)) {
                url = "/"; // always map the main dispatcherServlet to "/"
            }
            ServletRegistrationBean bean = new ServletRegistrationBean(source, url);
            bean.setMultipartConfig(this.multipartConfig);
            return bean;
        }

    }

    /**
     * {@link RegistrationBeanAdapter} for {@link Filter} beans.
     */
    private static class FilterRegistrationBeanAdapter
            implements RegistrationBeanAdapter<Filter> {

        @Override
        public RegistrationBean createRegistrationBean(String name, Filter source,
                                                       int totalNumberOfSourceBeans) {
            return new FilterRegistrationBean(source);
        }

    }

    /**
     * {@link RegistrationBeanAdapter} for certain {@link EventListener} beans.
     */
    private static class ServletListenerRegistrationBeanAdapter
            implements RegistrationBeanAdapter<EventListener> {

        @Override
        public RegistrationBean createRegistrationBean(String name, EventListener source,
                                                       int totalNumberOfSourceBeans) {
            return new ServletListenerRegistrationBean<EventListener>(source);
        }

    }

    private Servlet getServlet(ServletRegistrationBean object) {
        try {
            Field field = object.getClass().getDeclaredField("servlet");
            field.setAccessible(true);
            return (Servlet) field.get(object);
        } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private String getTargetFilterName(DelegatingFilterProxyRegistrationBean object) {
        try {
            Field field = object.getClass().getDeclaredField("targetBeanName");
            field.setAccessible(true);
            return (String) field.get(object);
        } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

}
