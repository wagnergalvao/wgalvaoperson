package br.com.wgalvao.wgalvaoperson.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

public class ErrorException extends HttpClientErrorException {

    public ErrorException(HttpStatus statusCode, String statusText) {
        super(statusCode, statusText);
    }

}
