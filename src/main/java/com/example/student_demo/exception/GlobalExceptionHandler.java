package com.example.student_demo.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidation(MethodArgumentNotValidException exception){

        Map<String, String> errors = new LinkedHashMap<>();

        exception.getBindingResult()
                .getFieldErrors()
                .forEach(error -> errors.put(error.getField(),
                        error.getDefaultMessage())
        );
        //        exception
        //  ↓ getBindingResult()
        //        校验结果
        //  ↓ getFieldErrors()
        //        字段错误列表
        //  ↓ forEach(...)
        //        逐个处理字段错误
        ErrorResponse response = new ErrorResponse(400,"请求参数校验失败",
                errors, LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);

    }

    public ResponseEntity<ErrorResponse> handleResponseStatus(ResponseStatusException exception){

        ErrorResponse response = new ErrorResponse(
                exception.getStatusCode().value(),
                exception.getReason()== null ? "请求处理失败" : exception.getReason(),
                Map.of(),
                LocalDateTime.now()
                );

        return ResponseEntity.status(exception.getStatusCode())
                .body(response);
    }
//现在两个处理方法各司其职：
//参数校验失败
//→ MethodArgumentNotValidException
//→ handleValidation()
//→ HTTP 400 + 字段错误
//
//学生不存在
//→ ResponseStatusException
//→ handleResponseStatus()
//→ HTTP 404 + 错误说明
}
