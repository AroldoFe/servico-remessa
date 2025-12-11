package br.com.aroldofe.servico_remessa.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class ErrorMessage {
    private String error;
    private String description;
    private String parameterName;
}
