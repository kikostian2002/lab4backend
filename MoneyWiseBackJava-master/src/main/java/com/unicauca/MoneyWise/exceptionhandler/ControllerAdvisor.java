package com.unicauca.MoneyWise.exceptionhandler;

import com.unicauca.MoneyWise.exceptions.UsuarioYaExisteExcepcion;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ControllerAdvisor {
    @ExceptionHandler(UsuarioYaExisteExcepcion.class)
    public ResponseEntity<ExceptionResponse> handleUsuarioYaExisteException(UsuarioYaExisteExcepcion ex) {
        ExceptionResponse response = new ExceptionResponse(
                ex.getMessage(),
                "400",
                LocalDateTime.now()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}
