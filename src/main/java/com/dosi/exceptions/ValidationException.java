package com.dosi.exceptions;

import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.util.List;

public class ValidationException extends RuntimeException {
    private BindingResult bindingResult;

    public ValidationException(BindingResult bindingResult) {
        this.bindingResult = bindingResult;
    }

    @Override
    public String getMessage() {
        StringBuilder sb = new StringBuilder();
        List<ObjectError> errors = bindingResult.getAllErrors();
        for (int i = 0; i < errors.size(); i++) {
            sb.append(errors.get(i).getDefaultMessage());
            if (i != errors.size() - 1) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }
}