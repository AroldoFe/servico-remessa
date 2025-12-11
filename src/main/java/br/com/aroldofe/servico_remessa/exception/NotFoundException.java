package br.com.aroldofe.servico_remessa.exception;

public class NotFoundException extends ApiException {
    public NotFoundException(String message) {
        super(message);
        this.errorResponse = new ErrorResponse(
                ErrorMessage.builder()
                        .error("entity_not_found")
                        .description(message)
                        .build()
        );
    }
}
