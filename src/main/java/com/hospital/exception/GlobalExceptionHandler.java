package com.hospital.exception;


import com.hospital.common.Result;
import jakarta.validation.ValidationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public Result<?> handleBusinessException(BusinessException e){
        return Result.error(e.getMessage());

    }

    @ExceptionHandler(NullPointerException.class)
    public Result<?> handleNullPoint(NullPointerException e){
        return Result.error("空指针异常");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result<?> handleValidException(MethodArgumentNotValidException e){
        String message =
                e.getBindingResult()
                        .getFieldError()
                        .getDefaultMessage();

        return Result.error(message);
    }

    @ExceptionHandler(Exception.class)
    public Result<?> hadnleException(Exception e){
        return Result.error("系统异常，请联系管理员");
    }


}
