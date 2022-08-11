package br.com.wgalvao.wgalvaoperson.exceptions.handler;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.wgalvao.wgalvaoperson.exceptions.ErrorException;
import br.com.wgalvao.wgalvaoperson.exceptions.ErrorExceptionResponse;
import br.com.wgalvao.wgalvaoperson.exceptions.ExceptionResponse;
import br.com.wgalvao.wgalvaoperson.exceptions.ResourceNotFoundException;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

        @ExceptionHandler(Exception.class)
        public final ResponseEntity<ExceptionResponse> handleAllExceptions(
                        Exception ex, WebRequest request) {

                ExceptionResponse exceptionResponse = new ExceptionResponse(
                                new Date(),
                                ex.getMessage(),
                                request.getDescription(false));

                return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        @ExceptionHandler(ErrorException.class)
        public final ResponseEntity<ErrorExceptionResponse> handleRuntimeException(
                        HttpClientErrorException ex, WebRequest request) {

                System.out.println(ex.getStatusCode());
                System.out.println(ex.getStatusText());
                ErrorExceptionResponse errorExceptionResponse = new ErrorExceptionResponse(
                                new Date(),
                                ex.getStatusCode(),
                                ex.getStatusText(),
                                ex.getMessage(),
                                request.getDescription(false));

                return new ResponseEntity<>(errorExceptionResponse, HttpStatus.BAD_REQUEST);
        }

        @ExceptionHandler(ResourceNotFoundException.class)
        public final ResponseEntity<ExceptionResponse> handleNotFoundExceptions(
                        Exception ex, WebRequest request) {

                ExceptionResponse exceptionResponse = new ExceptionResponse(
                                new Date(),
                                ex.getMessage(),
                                request.getDescription(false));

                return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
        }

}
