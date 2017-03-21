package com.sdcuike.practice.service;

import org.springframework.scheduling.annotation.Async;

import java.util.concurrent.Future;

/**
 * Created by beaver on 2017/3/20.
 */
public interface AsyncService {
    
    @Async
    Future<String> getXXX();
    
    String getXX();
}
