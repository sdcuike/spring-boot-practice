package com.sdcuike.practice.dependency;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Dependency Injection with scoped beans. <br>
 * Can be solved using a scoped proxy in place of the scoped bean
 * 
 * @author sdcuike 2017年2月17日
 */
@Component
public class SingletonComponent {
    @Autowired
    private ScopedComponent scopedComponent;

    public int getScopedBeanHasdCode() {
        return scopedComponent.getHashCode();
    }
}
