package com.chakshu.backend.exceptionHandling;

import com.chakshu.backend.errorResponse.ErrorResponse;
import com.chakshu.backend.exceptionClass.InvalidDataException;
import com.chakshu.backend.exceptionClass.RoleBasedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class ApiRolePermissionExceptionHandler extends GlobalExceptionHandler{

    @ExceptionHandler(RoleBasedException.class)
    public ResponseEntity<ErrorResponse> handleRoleBasedException (RoleBasedException roleBasedException) {
        String stackTrace = getStackTraceAsString(roleBasedException);
        ErrorResponse errorResponse = new ErrorResponse(roleBasedException.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),stackTrace);
        return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }

    @ExceptionHandler(InvalidDataException.class)
    public ResponseEntity<ErrorResponse> handleInvalidDataException (InvalidDataException invalidDataException) {
        String stackTrace = getStackTraceAsString(invalidDataException);
        ErrorResponse errorResponse = new ErrorResponse(invalidDataException.getMessage(),
                HttpStatus.NOT_ACCEPTABLE.ordinal(),stackTrace);
        return  ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(errorResponse);
    }

}
