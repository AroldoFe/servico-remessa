package br.com.aroldofe.servico_remessa.exception;

public class ContaAlreadyExistsException extends ApiException {
    public ContaAlreadyExistsException(String message) {
        super(message);
        this.errorResponse = new ErrorResponse(
                ErrorMessage.builder()
                        .error("conta_already_exists")
                        .description(message)
                        .build()
        );
    }
}
