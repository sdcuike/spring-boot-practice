package com.sdcuike.practice.controller.advice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.util.WebUtils;

import com.doctor.beaver.domain.result.BaseResult;

/**
 * 异常处理
 * 
 * @author sdcuike
 *         <p>
 *         Created on 2017-02-09 11:34:49<br>
 *         <p>
 *         {@link ResponseEntityExceptionHandler}
 */
@RestControllerAdvice
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {
    private final Logger log = LoggerFactory.getLogger(getClass());

    /**
     * 应用异常处理
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleSysException(Exception ex, WebRequest request) {
        BaseResult result = new BaseResult();
        com.sdcuike.practice.domain.ResponseStatus.SYS_ERROR.setResponseStatus(result);
        result.setInfo(ex.getMessage());

        log.error("handleException:{}", result, ex);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * spring mvc 内部异常处理
     */
    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
            request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, ex, WebRequest.SCOPE_REQUEST);
        }

        BaseResult result = new BaseResult();
        result.setReturnCode(com.sdcuike.practice.domain.ResponseStatus.INTERNAL_SERVER_ERROR.getValue());
        result.setReturnMsg(ex.getMessage());

        return new ResponseEntity<Object>(result, headers, status);
    }

}
