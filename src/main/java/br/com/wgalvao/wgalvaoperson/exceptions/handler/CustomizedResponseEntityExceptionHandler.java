package br.com.wgalvao.wgalvaoperson.exceptions.handler;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.wgalvao.wgalvaoperson.exceptions.ExceptionResponse;
import br.com.wgalvao.wgalvaoperson.exceptions.ResourceNotFoundException;

@ControllerAdvice
@Controller
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ExceptionResponse> handleAllExceptions(
            Exception exception, WebRequest webRequest) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                new Date(),
                exception.getMessage(),
                webRequest.getDescription(false));

        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public final ResponseEntity<ExceptionResponse> handleNotFoundExceptions(
            Exception exception,
            WebRequest webRequest,
            HttpStatus status) {

        return new ResponseEntity<>(buildExceptionResponse(exception, webRequest, status),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public final ExceptionResponse buildExceptionResponse(
            Exception exception, WebRequest webRequest, HttpStatus status) {

        System.out.println("getReasonPhrase " + status.getReasonPhrase());
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                new Date(),
                exception.getMessage(),
                webRequest.getDescription(false));

        return exceptionResponse;
    }
}
