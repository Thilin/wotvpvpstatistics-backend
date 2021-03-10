package com.mxh.wotvpvpstatisticsbackend.exceptions;

import lombok.Getter;

@Getter
public enum ExceptionEnum {

    RESOURCE_NOT_FOUND("/recurso-nao-encontrado", "Recurso não encontrado"),
    EMAIL_DUPLICATED("/email-duplicado", "Este email já foi utilizado");

    private String uri;
    private String description;
    ExceptionEnum(String path, String description){
        this.uri = "https://wotvpvpstatistics"+path;
        this.description = description;
    }
}
