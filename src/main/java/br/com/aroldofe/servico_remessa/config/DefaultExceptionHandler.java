package br.com.aroldofe.servico_remessa.config;

import br.com.aroldofe.servico_remessa.exception.ErrorMessage;
import br.com.aroldofe.servico_remessa.exception.ErrorResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Component
@RequiredArgsConstructor
@RestControllerAdvice
@Slf4j
public class DefaultExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handle(MethodArgumentNotValidException exception) {
        log.error(exception.getMessage(), exception);
        final var errorResponse = ErrorResponse.builder()
                .errorMessages(
                        exception.getBindingResult()
                                .getFieldErrors()
                                .stream()
                                .map(it -> ErrorMessage.builder()
                                        .error("validation_error")
                                        .parameterName(it.getField())
                                        .description(it.getDefaultMessage())
                                        .build())
                                .toList()
                ).build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(errorResponse);
    }
}
