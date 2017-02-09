package com.sdcuike.practice.web;

import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * web 组件配置
 * 
 * @author sdcuike
 *         <p>
 *         Created on 2017-02-09
 *         <p>
 *         web组件如Filter等注解配置，支持依赖注入
 */
@Configuration
@ServletComponentScan
public class WebComponentConfig {

}
