package com.sdcuike.springboot.practice.conditional.demo.properties;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @author sdcuike
 * @date 2018/1/28
 * @since 2018/1/28
 */
public class MySqlDriverPresentsCondition implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
        
    }
}
