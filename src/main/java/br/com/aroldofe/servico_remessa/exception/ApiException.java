package br.com.aroldofe.servico_remessa.exception;

import lombok.Getter;

public class ApiException extends RuntimeException {
    @Getter
    protected ErrorResponse errorResponse = new ErrorResponse();

    public ApiException(String message) {
        super(message);
    }
}
