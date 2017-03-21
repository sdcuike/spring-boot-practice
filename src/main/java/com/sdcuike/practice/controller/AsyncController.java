package com.sdcuike.practice.controller;

import com.sdcuike.practice.service.AsyncService;
import com.sdcuike.practice.service.impl.AsyncService2Impl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

/**
 * Created by beaver on 2017/3/20.
 */
@RestController
@RequestMapping(path = "/asyn",
        method = RequestMethod.POST,
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class AsyncController {
    @Autowired
    private AsyncService asyncService;
    
    @Autowired
    private AsyncService2Impl asyncService2;
    
    @Resource(name = "asyncExecutor")
    private Executor executor;
    
    @GetMapping("/testAsync")
    public List<String> testAsync() {
        Future<String> future1 = asyncService.getXXX();
        Future<String> future2 = asyncService.getXXX();
        Future<String> future3 = asyncService.getXXX();
        List<String> list = new ArrayList<>();
        try {
            list.add(future1.get());
            list.add(future2.get());
            list.add(future3.get());
        } catch (InterruptedException | ExecutionException e) {
            log.error("testAsync.error", e);
        }
        return list;
    }
    
    @GetMapping("/testAsync2")
    public List<String> testAsync2() {
        List<String> list = new ArrayList<>();
        String xx = asyncService.getXX();
        list.add(xx);
        return list;
    }
    
    @GetMapping("/testAsync-no-interface")
    public List<String> testAsyncNoInterface() {
        Future<String> future1 = asyncService2.getXXX();
        Future<String> future2 = asyncService2.getXXX();
        Future<String> future3 = asyncService2.getXXX();
        List<String> list = new ArrayList<>();
        try {
            list.add(future1.get());
            list.add(future2.get());
            list.add(future3.get());
        } catch (InterruptedException | ExecutionException e) {
            log.error("testAsync-no-interface.error", e);
        }
        return list;
    }
    
    @GetMapping("/testAsync2-no-interface")
    public List<String> testAsync2NoInterface() {
        List<String> list = new ArrayList<>();
        String xx = asyncService.getXX();
        list.add(xx);
        return list;
    }
    
    @GetMapping("/java8-parallelStream")
    public List<String> testJava8ParallelStream() {
        List<String> list = Arrays.asList("ni", "wo", "ta", "djfk");
        List<String> collect = list.parallelStream()
                                   .map(t -> t + Thread.currentThread().getName())
                                   .collect(Collectors.toList());
        
        return collect;
    }
    
    @GetMapping("/java8-completableFuture")
    public List<String> testJava8() {
        List<String> list = Arrays.asList("ni", "wo", "ta", "djfk");
        List<String> collect = list.stream()
                                   .map(t -> CompletableFuture.supplyAsync(() -> t + Thread.currentThread().getName(), executor))
                                   .map(CompletableFuture::join).collect(Collectors.toList());
        return collect;
    }
}
