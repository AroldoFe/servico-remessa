package br.com.aroldofe.servico_remessa.config;

import br.com.aroldofe.servico_remessa.exception.ApiException;
import br.com.aroldofe.servico_remessa.exception.ErrorMessage;
import br.com.aroldofe.servico_remessa.exception.ErrorResponse;
import br.com.aroldofe.servico_remessa.exception.NotFoundException;
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
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handle(Exception exception) {
        log.error(exception.getMessage(), exception);
        final var errorResponse = new ErrorResponse(
                ErrorMessage.builder()
                        .error("internal_server_error")
                        .description("Ocorreu um erro inesperado no servidor.")
                        .build()
        );

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(errorResponse);
    }

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

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ErrorResponse> handle(ApiException exception) {
        log.error(exception.getMessage(), exception);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(exception.getErrorResponse());
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handle(NotFoundException exception) {
        log.error(exception.getMessage(), exception);
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(exception.getErrorResponse());
    }
}
