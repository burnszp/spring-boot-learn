package com.friends.springbootexception;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
//@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MyException.class)
    @ResponseBody
    ResponseEntity<?> handleControllerException(HttpServletRequest request, MyException ex) {
        Map<String,Object> rMap = new HashMap<>();
        rMap.put("status",100);
        rMap.put("message",ex.getMessage());
        return new ResponseEntity<>(rMap, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
