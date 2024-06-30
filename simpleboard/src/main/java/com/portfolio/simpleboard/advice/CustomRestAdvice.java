package com.portfolio.simpleboard.advice;

import lombok.extern.log4j.Log4j2;
import org.apache.coyote.Response;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@RestControllerAdvice
@Log4j2
public class CustomRestAdvice {
    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.EXPECTATION_FAILED)
    public ResponseEntity<Map<String, String>> handleBindException(BindException e) {
        log.error(e);
        Map<String, String> retMap = new HashMap<>();
        if(e.hasErrors()) {
            BindingResult bindingResult= e.getBindingResult();
            bindingResult.getFieldErrors().forEach(x->{
                retMap.put(x.getField(), x.getCode());
            });
        }
        return ResponseEntity.badRequest().body(retMap);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.EXPECTATION_FAILED)
    public ResponseEntity<Map<String, String>> handleFKException(Exception e) {
        log.error(e);

        Map<String, String> err = new HashMap<>();
        err.put("time", "%d".formatted(System.currentTimeMillis()));
        err.put("msg", "constraint failed");
        return ResponseEntity.badRequest().body(err);
    }

    @ExceptionHandler(
            {
                NoSuchElementException.class
                , EmptyResultDataAccessException.class
            }
    )
    @ResponseStatus(HttpStatus.EXPECTATION_FAILED)
    public ResponseEntity<Map<String, String>> handleNoSuchElement(Exception e) {
        Map<String, String> err = new HashMap<>();

        err.put("time", "%d".formatted(System.currentTimeMillis()));
        err.put("msg", "No such element expectation");

        return ResponseEntity.badRequest().body(err);
    }

}
