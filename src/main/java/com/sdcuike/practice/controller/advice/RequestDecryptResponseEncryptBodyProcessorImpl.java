package com.sdcuike.practice.controller.advice;

import java.nio.charset.Charset;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

@Component
public class RequestDecryptResponseEncryptBodyProcessorImpl extends RequestDecryptResponseEncryptBodyProcessor {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Override
    protected String doDecryptRequestBody(String input, HttpHeaders httpHeaders, Charset charset, MethodParameter parameter) {
        log.info(getClass() + "doDecryptRequestBody");
        return super.doDecryptRequestBody(input, httpHeaders, charset, parameter);
    }

    @Override
    protected Object doEncryptResponseBody(Object input, HttpHeaders httpHeaders, Charset charset, MethodParameter parameter) {
        log.info(getClass() + "doEncryptResponseBody");
        return super.doEncryptResponseBody(input, httpHeaders, charset, parameter);
    }

}
