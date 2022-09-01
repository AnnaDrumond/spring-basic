package com.io.github.annadrumond.springbasic.controllers.exceptions;

import com.io.github.annadrumond.springbasic.services.UserService;
import com.io.github.annadrumond.springbasic.services.exceptions.DatabaseException;
import com.io.github.annadrumond.springbasic.services.exceptions.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.Level;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;
import java.util.logging.Logger;


/**
 * Using @ExceptionHandler
 * You can add extra (@ExceptionHandler) methods to any controller to specifically handle exceptions thrown by request handling
 * (@RequestMapping) methods in the same controller. Such methods can:
 *
 * Handle exceptions without the @ResponseStatus annotation (typically predefined exceptions that you didn’t write)
 * Redirect the user to a dedicated error view
 * Build a totally custom error response
 */
////Tratamento de exceção baseado em controlador
@ControllerAdvice //interceptador de exceções lançadas por métodos anotados com @RequestMapping e similares.
@Slf4j //fazer log da execução
//https://zetcode.com/springboot/controlleradvice/
public class ResourceExceptionHandler {

    //https://spring.io/blog/2013/11/01/exception-handling-in-spring-mvc
   @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException resourceNotFoundException,
                                           HttpServletRequest httpServletRequest){

       return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new StandardError(Instant.now(), HttpStatus.NOT_FOUND.value(),
               resourceNotFoundException.getMessage(), "Resource Not Found", httpServletRequest.getRequestURI()));

    }


    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<StandardError> dataBaseException(DatabaseException databaseException,
                                                          HttpServletRequest httpServletRequest) {

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new StandardError(Instant.now(), HttpStatus.BAD_REQUEST.value(),
                databaseException.getMessage(), "Data base error", httpServletRequest.getRequestURI()));
    }

}
