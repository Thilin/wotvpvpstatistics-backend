package com.mxh.wotvpvpstatisticsbackend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EmailDuplicatedException extends RuntimeException{
    public EmailDuplicatedException(String s){
        super(s);
    }
}
