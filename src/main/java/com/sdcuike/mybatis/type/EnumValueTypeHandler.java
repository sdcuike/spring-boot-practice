package com.sdcuike.mybatis.type;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 不要修改，只继承，并添加注解MappedTypes
 * Created by beaver on 2017/4/19.
 */
public abstract class EnumValueTypeHandler extends BaseTypeHandler<IEnumValueType> {
    private Class<IEnumValueType> type;
    
    @SuppressWarnings("unchecked")
    public EnumValueTypeHandler() {
        MappedTypes annotation = getClass().getAnnotation(MappedTypes.class);
        if (annotation == null) {
            throw new RuntimeException("typehandler:" + getClass().getName() + " MappedTypes annotation value is empty ");
        }
        
        type = (Class<IEnumValueType>) annotation.value()[0];
        
    }
    
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, IEnumValueType parameter, JdbcType jdbcType) throws SQLException {
        ps.setInt(i, parameter.getValue());
        
    }
    
    @Override
    public IEnumValueType getNullableResult(ResultSet rs, String columnName) throws SQLException {
        int index = rs.getInt(columnName);
        return IEnumValueType.of(type, index);
    }
    
    @Override
    public IEnumValueType getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        int index = rs.getInt(columnIndex);
        return IEnumValueType.of(type, index);
    }
    
    @Override
    public IEnumValueType getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        int index = cs.getInt(columnIndex);
        return IEnumValueType.of(type, index);
    }
    
}
