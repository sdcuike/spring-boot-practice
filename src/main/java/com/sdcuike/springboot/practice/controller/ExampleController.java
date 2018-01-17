package com.sdcuike.springboot.practice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author sdcuike
 * @date 2018/01/17
 * @since 2018/1/17
 */
@RestController
@RequestMapping("/hello")
public class ExampleController {

    @GetMapping("/")
    public String hello() {
        return "hello sdcuike";
    }
}
