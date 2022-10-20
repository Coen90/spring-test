package com.coen.cloud.standard.core.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.text.ParsePosition;

@Slf4j
@ControllerAdvice("com.coen.cloud.standard.controller.web")
public class ControllerExceptionAdvice {

    @ExceptionHandler(Exception.class)
    public String Exception(ModelMap modelMap, Exception e) {
        modelMap.addAttribute("message", e.getMessage());

        return "error";

    }
}
