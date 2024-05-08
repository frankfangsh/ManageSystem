package com.example.managesystem.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.ServletException;


@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    public static final int PARAMS_ERROR_CODE = 400;
    public static final String PARAMS_ERROR_MSG = "params error!";
    public static final int SYSTEM_ERROR_CODE = 500;
    public static final String SYSTEM_ERROR_MSG = "system error!";

    /**
     * 系统异常
     */
    @ExceptionHandler(Throwable.class)
    public BaseResponse handleException(Throwable e) throws Throwable {
        if (e instanceof ServletException) {
            throw e;
        } else if (e instanceof HttpMessageNotReadableException) {
            return BaseResponse.fail(PARAMS_ERROR_CODE, PARAMS_ERROR_MSG);
        } else {
            log.error("system error:{}", e);
            return BaseResponse.fail(SYSTEM_ERROR_CODE, SYSTEM_ERROR_MSG);
        }
    }


    /**
     * 处理接口参数异常
     */
    @ExceptionHandler({MethodArgumentNotValidException.class, BindException.class})
    public BaseResponse handleMethodArgumentNotValidException(Exception e) {
        try {
            String errorMsg;
            if (e instanceof MethodArgumentNotValidException) {
                errorMsg = ((MethodArgumentNotValidException) e).getBindingResult().getFieldError().getDefaultMessage();
            } else {
                errorMsg = ((BindException) e).getBindingResult().getFieldError().getDefaultMessage();
            }
            log.error("参数异常:{}", errorMsg);
            return BaseResponse.fail(PARAMS_ERROR_CODE, errorMsg);
        } catch (Exception ex) {
            log.error("system error:{}", e);
            return BaseResponse.fail(PARAMS_ERROR_CODE, SYSTEM_ERROR_MSG);
        }
    }

    /**
     * 业务异常
     */
    @ExceptionHandler(BusinessException.class)
    public BaseResponse handleBusinessException(BusinessException e) {
        if (e.getCode() != 0) {
            return BaseResponse.fail(e.getCode(), e.getMessage());
        }
        if (!StringUtils.isEmpty(e.getMessage())) {
            return BaseResponse.fail(e.getMessage());
        } else {
            log.error("system error:{}", e);
            return BaseResponse.fail(SYSTEM_ERROR_MSG);
        }
    }

}
