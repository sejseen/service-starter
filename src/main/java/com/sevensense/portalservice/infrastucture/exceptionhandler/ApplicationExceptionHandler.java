package com.sevensense.portalservice.infrastucture.exceptionhandler;

import com.sevensense.portalservice.domain.exceptions.PortalServiceException;
import com.sevensense.portalservice.domain.exceptions.ResourceNotFoundException;
import com.sevensense.portalservice.domain.exceptions.TokenExpiredException;
import com.sevensense.portalservice.domain.exceptions.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

    private static final String GLOBAL_EXCEPTION_RESPONSE_BODY = "Service unavailable - undesired service behaviour";
    private static final String TOKEN_EXPIRED_RESPONSE_BODY = "Invalid token - expired or not found";

    @ExceptionHandler(PortalServiceException.class)
    public ResponseEntity<Object> handleGlobalException(RuntimeException ex, WebRequest request) {
        return new ResponseEntity<>(GLOBAL_EXCEPTION_RESPONSE_BODY, HttpStatus.SERVICE_UNAVAILABLE);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handleResourceNotFound(RuntimeException ex, WebRequest request) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<Object> handleValidationException(ValidationException ex, WebRequest request) {
        return new ResponseEntity<>(ex.getErrors(), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(TokenExpiredException.class)
    public ResponseEntity<Object> handleTokenExpiration(RuntimeException ex, WebRequest request) {
        return new ResponseEntity<>(TOKEN_EXPIRED_RESPONSE_BODY, HttpStatus.UNAUTHORIZED);
    }

}
