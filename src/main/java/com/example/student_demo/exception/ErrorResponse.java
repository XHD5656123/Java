package com.example.student_demo.exception;

import java.time.LocalDateTime;
import java.util.Map;

public record ErrorResponse(
    int code,
    String message,
    Map<String, String> errors,
    LocalDateTime timestamp)
 {
}
