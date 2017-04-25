package com.sdcuike.mybatis.auditor;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;

import java.time.LocalDateTime;
import java.util.Properties;

/**
 * Created by beaver on 2017/4/25.
 */
@Intercepts({@Signature(
        type = Executor.class,
        method = "update",
        args = {MappedStatement.class, Object.class})})
public class AuditingInterceptor implements Interceptor {
    
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
        SqlCommandType sqlCommandType = mappedStatement.getSqlCommandType();
        Object parameter = invocation.getArgs()[1];
        if (!(parameter instanceof AbstractAuditingEntity)) {
            return invocation.proceed();
        }
        
        AbstractAuditingEntity auditingEntity = (AbstractAuditingEntity) parameter;
        LocalDateTime currentDateTime = LocalDateTime.now();
        String userId = AuditorServiceUtis.getUserId();
        
        if (SqlCommandType.UPDATE == sqlCommandType) {
            auditingEntity.setLastmodifiedBy(userId);
            auditingEntity.setLastmodifiedTime(currentDateTime);
            
        } else if (SqlCommandType.INSERT == sqlCommandType) {
            auditingEntity.setCreateBy(userId);
            auditingEntity.setCreateTime(currentDateTime);
        }
        return invocation.proceed();
    }
    
    
    @Override
    public Object plugin(Object target) {
        if (target instanceof Executor) {
            return Plugin.wrap(target, this);
        } else {
            return target;
        }
    }
    
    @Override
    public void setProperties(Properties properties) {
    
    }
}
