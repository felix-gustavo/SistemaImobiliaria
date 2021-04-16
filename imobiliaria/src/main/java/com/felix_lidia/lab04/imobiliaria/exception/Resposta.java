package com.felix_lidia.lab04.imobiliaria.exception;

import java.util.List;

import lombok.Getter;

@Getter
public class Resposta {
    private Integer status;
    private String error;
    private String message;
    private List<ErroDeValidacao> errors;

    public Resposta(Integer status, String error, String message, List<ErroDeValidacao> errors) {
        this.status = status;
        this.error = error;
        this.message = message;
        this.errors = errors;
    }

    public Resposta(Integer status, String error, String message) {
        this.status = status;
        this.error = error;
        this.message = message;
    }
}