package com.sdcuike.mybatis.type;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * mybatis统一处理自定义枚举<br>
 *
 * @see org.apache.ibatis.type.EnumTypeHandler <br>
 * @see org.apache.ibatis.type.EnumOrdinalTypeHandler <br>
 * <p>
 * Created by beaver on 2017/4/19.
 */
public interface IEnumValueType {
    @JsonValue
    int getValue();
    
    public static <T extends IEnumValueType> T of(Class<T> type, int value) {
        T[] constants = type.getEnumConstants();
        for (T t : constants) {
            if (t.getValue() == value) {
                return t;
            }
        }
        
        throw new RuntimeException(type + " not have a valid index value :" + value);
    }
}
