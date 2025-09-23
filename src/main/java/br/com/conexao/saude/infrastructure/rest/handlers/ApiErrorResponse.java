package br.com.conexao.saude.infrastructure.rest.handlers;

import java.time.LocalDateTime;

public record ApiErrorResponse(LocalDateTime timestamp, int status, String error, String trace) {

    public ApiErrorResponse(int status, String error, String trace) {
        this(LocalDateTime.now(), status, error, trace);
    }

}