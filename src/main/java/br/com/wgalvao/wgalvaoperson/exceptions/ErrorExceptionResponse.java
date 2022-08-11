package br.com.wgalvao.wgalvaoperson.exceptions;

import java.io.Serializable;
import java.util.Date;

import org.springframework.http.HttpStatus;

public class ErrorExceptionResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private Date timeStampDate;
    private HttpStatus statusCode;
    private String statusText;
    private String message;
    private String details;

    public ErrorExceptionResponse(Date timeStampDate, HttpStatus statusCode, String statusText, String message,
            String details) {
        this.timeStampDate = timeStampDate;
        this.statusCode = statusCode;
        this.statusText = statusText;
        this.message = message;
        this.details = details;
    }

    public Date getTimeStampDate() {
        return timeStampDate;
    }

    public void setTimeStampDate(Date timeStampDate) {
        this.timeStampDate = timeStampDate;
    }

    public HttpStatus getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(HttpStatus statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusText() {
        return statusText;
    }

    public void setStatusText(String statusText) {
        this.statusText = statusText;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

}
