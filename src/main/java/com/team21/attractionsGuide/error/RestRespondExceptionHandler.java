package com.team21.attractionsGuide.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


/**
 * This class handles exceptions thrown by REST API requests and generates custom error responses.
 * @author Hei Lam
 * Date:  06/04/2023
 */
@ControllerAdvice
@ResponseStatus
public class RestRespondExceptionHandler extends ResponseEntityExceptionHandler {


    /**
     *
     * @param commentWithTheLocationIdNotFoundException The exception object containing the error message.
     * @return a ResponseEntity object containing the custom error message and a 404 HTTP status code.
     */
    @ExceptionHandler(CommentWithTheLocationIdNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleCommentNotFoundException(CommentWithTheLocationIdNotFoundException commentWithTheLocationIdNotFoundException) {

        ErrorMessage message = new ErrorMessage(HttpStatus.NOT_FOUND, commentWithTheLocationIdNotFoundException.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
    }
}
