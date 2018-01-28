package com.sdcuike.springboot.practice.conditional.demo.properties;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @author sdcuike
 * @date 2018/1/28
 * @since 2018/1/28
 */
public class MongoDbTypeCondition implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        final String dbType = System.getProperty("dbType");
        return "mongo".equalsIgnoreCase(dbType);
    }
}
