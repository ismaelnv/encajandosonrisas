package com.example.demo.EncajandoSonrisasExceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class EncajandoSonrisasExceptionsController {

    private static final Logger LOG = LoggerFactory.getLogger(EncajandoSonrisasExceptionsController.class.getName());

    @ExceptionHandler(value = {EncajandoSonrisasBadRequestExceptions.class})
    public ResponseEntity<ExceptionDetails> EncajandoSonrisasBadRequestExcepcion(EncajandoSonrisasBadRequestExceptions ex) {
        
        LOG.error(ex.getMessage(), ex);
        return new ResponseEntity<>(ex.getDetails(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {EncajandoSonrisasNotFountExeptions.class})
    public ResponseEntity<ExceptionDetails> EncajandoSonrisasNotFountExeption(EncajandoSonrisasNotFountExeptions ex) {
        
        LOG.error(ex.getMessage(), ex);
        return new ResponseEntity<>(ex.getDetails(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {Exception.class, RuntimeException.class})
    public ResponseEntity<ExceptionDetails> EncajandoSonrisasGeneralException(Throwable ex) {
        
        LOG.error(ex.getMessage(), ex);
        var details = new ExceptionDetails("Ha ocurrido un error inesperado, por favor contacte al aministrador", "ERROR");
        return new ResponseEntity<>(details,  HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
