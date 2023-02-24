package com.dosi.controllers;

import com.dosi.services.BaseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

public abstract class GlobalController<T, K> extends BaseController{

    public GlobalController(BaseService service) {
        super(service);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable K id) {
        service.delete(id);
    }

    @GetMapping("/{id}")
    public T read(@PathVariable K id) {
        return (T)service.read(id);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        System.out.println("*******************");
        System.out.println( ex.getMessage());
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }
}