package br.com.aroldofe.servico_remessa.exception;

public class InsufficientFundsException extends ApiException {
    public InsufficientFundsException(String message) {
        super(message);
        this.errorResponse = new ErrorResponse(
                ErrorMessage.builder()
                        .error("insufficient_funds")
                        .description(message)
                        .build()
        );
    }
}
