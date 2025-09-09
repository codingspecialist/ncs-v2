package com.getinthere.ncs._core.errors;

import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import com.getinthere.ncs._core.errors.ex.Exception400;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
@Component
public class MyValidationHandler {
    @Before("@annotation(org.springframework.web.bind.annotation.PostMapping) || @annotation(org.springframework.web.bind.annotation.PutMapping)")
    public void badRequestAdvice(JoinPoint jp) {
        Object[] args = jp.getArgs();
        for (Object arg : args) {

            if (arg instanceof Errors) {
                Errors errors = (Errors) arg;
                if (errors.hasErrors()) {
                    List<FieldError> fErrors = errors.getFieldErrors();

                    for (FieldError fieldError : fErrors) {
                        log.error(fieldError.getField() + ":" + fieldError.getDefaultMessage());
                        throw new Exception400(fieldError.getField() + ":" + fieldError.getDefaultMessage());
                    }
                }
            }
        }
    }
}
