package com.sdcuike.springboot.practice.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author sdcuike
 * @date 2018/1/23
 * @since 2018/1/23
 */
@Component
public class GlobalValue {
    private final Logger LOG = LoggerFactory.getLogger(getClass());
    
    public static String globalValue;
    
    @Value("${global.demo.value}")
    public  void setLogConfig(String globalValue) {
        GlobalValue.globalValue = globalValue;
    }
    
    @PostConstruct
    public void init() {
        LOG.info("======= globalValue  "+ globalValue);
    }
}
