package com.sdcuike.practice.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * Created by beaver on 2017/3/20.
 */
@Service("asyncService2")
@Slf4j
public class AsyncService2Impl {
    
    
    private Random random = new Random();
    
    @Async
    public Future<String> getXXX() {
        
        int nextInt = random.nextInt(5);
        try {
            
            TimeUnit.SECONDS.sleep(nextInt);
            log.info("currentThread " + Thread.currentThread().getName());
        } catch (InterruptedException e) {
            log.error("TimeUnit.SECONDS.sleep.error", e);
        }
        return new AsyncResult<>("currentThread " + Thread.currentThread().getName());
    }
    
    public String getXX() {
        try {
            return getXXX().get();
        } catch (InterruptedException | ExecutionException e) {
            log.error("getXX.error", e);
        }
        
        return "";
    }
    
}
