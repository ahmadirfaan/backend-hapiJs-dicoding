package com.openmusic.api.controllers;

import com.openmusic.api.exception.*;
import com.openmusic.api.models.response.ResponseMessage;
import io.jsonwebtoken.MalformedJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Ahmad Irfaan Hibatullah
 * @version $Id: DefaultExceptionHandler.java, v 0.1 2021‐11‐08 21.28 Ahmad Irfaan Hibatullah Exp $$
 */
@ControllerAdvice
public class DefaultExceptionHandler extends ResponseEntityExceptionHandler {

    private final MessageSource messageSource;

    @Autowired
    public DefaultExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    private ResponseEntity<Object> handleException(HttpStatus status) {
        String message;
        int value = status.value();
        if(value == 401) {
            message = "Maaf anda tidak tidak terautorisasi untuk mengakses ini";
        } else if(value == 400) {
            message = "Client bermasalah";
        } else if(value == 500) {
            message = "Internal Server Error";
        }
        else {
            message = "Server error";
        }
        return handleException(status, message);
    }

    private ResponseEntity<Object> handleException(HttpStatus status, String message) {
        ResponseMessage<Object> body = ResponseMessage.error("fail", message);
        return ResponseEntity.status(status.value()).body(body);
    }

    private ResponseEntity<Object> handleExceptionFromMessageSource(HttpStatus status, String message) {
        String localizedMessage = messageSource.
                getMessage(message, null, message, LocaleContextHolder.getLocale());
        ResponseMessage<Object> body = ResponseMessage.error("fail", localizedMessage);
        return ResponseEntity.status(status.value()).body(body);
    }

    private ResponseEntity<Object> handleBindingResult(BindingResult result, HttpStatus status) {
        Map<String, List<String>> errors = new HashMap<>();
        result.getFieldErrors().forEach((fieldError) -> {
            String name = fieldError.getField();
            String value = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
            List<String> messages = errors.computeIfAbsent(name, k -> new ArrayList<>());

            messages.add(value);
        });

        String message = messageSource.getMessage("error." + status.value(),
                null, LocaleContextHolder.getLocale());
        ResponseMessage<Object> body = ResponseMessage.error("fail", message, errors);
        return ResponseEntity.status(status.value()).body(body);
    }

    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<Object> handleApplicationException(ApplicationException ex) {
        logger.error("Application Exception : ", ex);
        return handleException(ex.getStatus(), ex.getReason());
    }

    @ExceptionHandler(MissingRequestHeaderException.class)
    public ResponseEntity<Object> handleMissingRequestHeaderException(MissingRequestHeaderException ex) {
        logger.error("Missing Request Header Exception : ", ex);
        return handleException(HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(ClientException.class)
    public ResponseEntity<Object> handleClientException(ClientException ex) {
        logger.error("Client Exception : ", ex);
        return handleException(ex.getStatus(), ex.getReason());
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException ex) {
        logger.error("Entity Not Found Exception : ", ex);
        return handleException(ex.getStatus(), ex.getReason());
    }

    @ExceptionHandler(PathNotFoundException.class)
    public ResponseEntity<Object> handlePathNotFoundException(PathNotFoundException ex) {
        logger.error("Path Not Found Exception : ", ex);
        return handleException(ex.getStatus(), ex.getReason());
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<Object> handleAuthenticationException(AuthenticationException ex) {
        logger.error("Authentication Exception : ", ex);
        return handleException(ex.getStatus(), ex.getReason());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleUnknownException(Exception e) {
        logger.error("Unknown Exception : ", e);
        return handleException(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MalformedJwtException.class)
    public ResponseEntity<Object> handleUnknownException(MalformedJwtException e) {
        logger.error("Malformed JWT Exception : ", e);
        return handleException(HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return handleException(status);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return handleBindingResult(ex.getBindingResult(), status);
    }

    @Override
    protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return handleBindingResult(ex, status);
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return handleExceptionFromMessageSource(status, ex.getMessage());
    }
}
