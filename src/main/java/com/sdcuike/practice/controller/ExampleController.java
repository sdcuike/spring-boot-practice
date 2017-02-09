package com.sdcuike.practice.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.doctor.beaver.domain.result.ModelResult;
import com.google.common.collect.Lists;
import com.sdcuike.practice.config.CommonConfig;
import com.sdcuike.practice.dao.CityRepository;
import com.sdcuike.practice.domain.City;

@RestController
@RequestMapping("example")
public class ExampleController {
    private final Logger   log = LoggerFactory.getLogger(getClass());

    @Resource
    private CommonConfig   commonConfig;

    @Resource
    private CityRepository cityRepository;

    @RequestMapping("/")
    public ModelResult<String> home() {
        ModelResult<String> modelResult = new ModelResult<>();
        modelResult.setData("hello world spring boot");
        return modelResult;
    }

    @RequestMapping("/testConfig")
    public ModelResult<String> testConfig() {
        ModelResult<String> modelResult = new ModelResult<>();
        modelResult.setData(commonConfig.getAppName());
        return modelResult;
    }

    @RequestMapping("/testConfigN")
    public ModelResult<String> testConfig_(@RequestHeader("headerN") String headerN) {
        ModelResult<String> modelResult = new ModelResult<>();
        modelResult.setData(commonConfig.getAppName());
        return modelResult;
    }

    @RequestMapping("/testConfigE")
    public ModelResult<String> testConfig_E() {
        ModelResult<String> modelResult = new ModelResult<>();
        modelResult.setData(commonConfig.getAppName());
        throw new RuntimeException("test e");
    }

    @RequestMapping("/db")
    public ModelResult<List<City>> testJPA() {
        ModelResult<List<City>> modelResult = new ModelResult<>();
        MDC.put("WHO", "WHO");
        log.info("testJPA");
        cityRepository.save(new City("name", "city"));
        Iterable<City> iterable = cityRepository.findAll();
        ArrayList<City> list = Lists.newArrayList(iterable);
        modelResult.setData(list);
        return modelResult;
    }
}
