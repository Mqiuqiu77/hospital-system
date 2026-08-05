package com.hospital.exception;


import com.hospital.common.Result;
import jakarta.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MultipartException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MultipartException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result<?> handleMultipartException(MultipartException e) {
        log.warn("Multipart请求解析失败: {}", e.getMessage());
        Result<?> result = Result.error("上传请求格式错误：请使用 multipart/form-data，文件字段名为 file，且不要手动设置 Content-Type");
        result.setCode(HttpStatus.BAD_REQUEST.value());
        return result;
    }

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

        log.error("系统异常",e);
        return Result.error("系统异常，请联系管理员");
    }


}
