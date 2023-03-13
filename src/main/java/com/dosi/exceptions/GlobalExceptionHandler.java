package com.dosi.exceptions;

import static com.dosi.utils.Constants.*;
import com.fasterxml.jackson.databind.JsonMappingException;
import org.springframework.boot.json.JsonParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({ Exception.class})
    public ResponseEntity<Map<String, String>> handleValidationExceptions(Exception ex) {
        Map<String, String> response = new HashMap<>();
        System.out.println(ex);
        ex.printStackTrace();
        if( ex instanceof BadCredentialsException){
            response.put("message","Veuillez vérifier votre adresse e-mail et votre mot de passe et réessayer.");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }

        else if (ex.getCause() != null && ex.getCause().getMessage() != null && ex.getCause().getMessage().contains("DateTimeParseException")) {
            response.put("message", "Le format de la date doit être" + DATE_PATTERN + ".");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        } else if (ex instanceof JsonParseException || ex instanceof JsonMappingException || ex instanceof HttpMessageNotReadableException) {
            response.put("message", "Format JSON invalide.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        } else if (ex instanceof NullPointerException) {
            response.put("message", "Quelque chose s'est mal passé, veuillez réessayer plus tard.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }  else if( ex instanceof ResponseStatusException){
            response.put("message", ex.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        } else {
            response.put("message", ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }
}
