package com.sdcuike.practice.controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sdcuike.practice.config.CommonConfig;
import com.sdcuike.practice.dao.CityRepository;
import com.sdcuike.practice.domain.City;

@RestController
public class ExampleController {
    private final Logger   log = LoggerFactory.getLogger(getClass());

    @Resource
    private CommonConfig   commonConfig;

    @Resource
    private CityRepository cityRepository;

    @RequestMapping("/")
    public String home() {
        return "hello world spring boot";
    }

    @RequestMapping("/testConfig")
    public String testConfig() {
        return commonConfig.getAppName();
    }

    @RequestMapping("/db")
    public Iterable<City> testJPA() {
        MDC.put("WHO", "WHO");
        log.info("testJPA");
        cityRepository.save(new City("name", "city"));
        return cityRepository.findAll();
    }
}
