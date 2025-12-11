package br.com.aroldofe.servico_remessa.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

@AllArgsConstructor
@Builder
@Getter
public class ErrorResponse {
    private Collection<ErrorMessage> errorMessages;

    public ErrorResponse(ErrorMessage ...errorMessage) {
        this.errorMessages = Arrays.asList(errorMessage);
    }
}
