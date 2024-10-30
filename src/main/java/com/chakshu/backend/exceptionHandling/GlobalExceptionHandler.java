package com.chakshu.backend.exceptionHandling;

import com.chakshu.backend.errorResponse.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.PrintWriter;
import java.io.StringWriter;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleError (Exception exception)  {
        String stackTrace = getStackTraceAsString(exception);
        ErrorResponse errorResponse = new ErrorResponse(exception.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),stackTrace);
       return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }


    protected String getStackTraceAsString(Exception ex) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        ex.printStackTrace(pw);
        return sw.toString();
    }

}
