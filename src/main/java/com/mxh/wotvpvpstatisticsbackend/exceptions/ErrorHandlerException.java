package com.mxh.wotvpvpstatisticsbackend.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.OffsetDateTime;

@RestControllerAdvice
public class ErrorHandlerException extends ResponseEntityExceptionHandler {

    private Error.ErrorBuilder createErrorBuilder(HttpStatus status, ExceptionEnum exception, String message){
        return Error.builder().timestamp(OffsetDateTime.now()).status(status.value()).type(exception.getUri())
                .title(exception.getDescription()).msg(message);
    }

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<Object> handleException(ObjectNotFoundException ex, WebRequest request){
        HttpStatus status = HttpStatus.NOT_FOUND;
        ExceptionEnum errorType = ExceptionEnum.RESOURCE_NOT_FOUND;
        String msg = ex.getMessage();
        Error errors = createErrorBuilder(status, errorType, msg).build();
        return handleExceptionInternal(ex, errors, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(EmailDuplicatedException.class)
    public ResponseEntity<Object> handleException(EmailDuplicatedException ex, WebRequest request){
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ExceptionEnum errorType = ExceptionEnum.EMAIL_DUPLICATED;
        String msg = ex.getMessage();
        Error errors = createErrorBuilder(status, errorType, msg).build();
        return handleExceptionInternal(ex, errors, new HttpHeaders(), status, request);
    }
}
