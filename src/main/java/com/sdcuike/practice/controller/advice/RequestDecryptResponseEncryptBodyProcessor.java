package com.sdcuike.practice.controller.advice;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

import org.apache.commons.io.IOUtils;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;

public abstract class RequestDecryptResponseEncryptBodyProcessor {
    public final String decryptRequestBody(InputStream inputStream, HttpHeaders httpHeaders, Charset charset, MethodParameter parameter) throws IOException {
        String input = IOUtils.toString(inputStream, charset);
        return doDecryptRequestBody(input, httpHeaders, charset, parameter);
    }

    public final Object encryptResponseBody(Object input, HttpHeaders httpHeaders, Charset charset, MethodParameter parameter) {
        return doEncryptResponseBody(input, httpHeaders, charset, parameter);
    }

    protected String doDecryptRequestBody(String input, HttpHeaders httpHeaders, Charset charset, MethodParameter parameter) {
        return input;
    }

    protected Object doEncryptResponseBody(Object input, HttpHeaders httpHeaders, Charset charset, MethodParameter parameter) {
        return input;
    }
}
