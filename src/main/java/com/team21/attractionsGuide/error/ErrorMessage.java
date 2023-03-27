package com.team21.attractionsGuide.error;

import org.springframework.http.HttpStatus;

/**
 * A custom error message class that contains a message and an HTTP status code.
 * use in the RestRespondExceptionHandler
 */
public class ErrorMessage {

    //the store the httpStatus code
    private HttpStatus HttpStatusCode;
    // default message
    private String message;

    /**
     * No argument constructor
     */
    public ErrorMessage() {
    }

    public ErrorMessage(HttpStatus HttpStatusCode, String message) {
        this.HttpStatusCode = HttpStatusCode;
        this.message = message;
    }

    /**
     * Getter and setter
     */

    public HttpStatus getHttpStatusCode() {
        return HttpStatusCode;
    }


    public void setHttpStatusCode(HttpStatus httpStatusCode) {
        HttpStatusCode = httpStatusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
