package com.sdcuike.practice.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.sdcuike.mybatis.type.IEnumValueType;
import lombok.Getter;
import lombok.ToString;

/**
 * Created by beaver on 2017/4/19.
 */

@Getter
@ToString
public enum CompanyType implements IEnumValueType {
    A(1, "类型1"),
    B(2, "类型2");
    
    private int code;
    private String name;
    
    CompanyType(int code, String name) {
        this.code = code;
        this.name = name;
    }
    
    @Override
    public int getValue() {
        return code;
    }
    
    @JsonCreator
    public static CompanyType ofValue(int value) {
        return IEnumValueType.of(CompanyType.class, value);
    }
}
