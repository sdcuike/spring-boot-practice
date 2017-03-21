package com.sdcuike.practice.service.impl;

import com.sdcuike.practice.service.AsyncService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * Created by beaver on 2017/3/20.
 */
@Service("asyncService")
@Slf4j
public class AsyncServiceImpl implements AsyncService {
    
    private Random random = new Random();
    
    @Override
    public Future<String> getXXX() {
        
        int nextInt = random.nextInt(5);
        try {
            
            TimeUnit.SECONDS.sleep(nextInt);
            log.info("currentThread "+ Thread.currentThread().getName());
        } catch (InterruptedException e) {
            log.error("TimeUnit.SECONDS.sleep.error", e);
        }
        return new AsyncResult<>("currentThread "+ Thread.currentThread().getName());
    }
    
    /**
     * 这种内部调用内部的异步方法无效，代理失效
     * @return
     */
    @Override
    public String getXX() {
        try {
            return getXXX().get();
        } catch (InterruptedException |ExecutionException  e) {
            log.error("getXX.error",e);
        }
        
        return  "";
    }
}
