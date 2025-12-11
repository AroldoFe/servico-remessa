package br.com.aroldofe.servico_remessa.exception;

public class UsuarioAlreadyExists extends ApiException {
    public UsuarioAlreadyExists(String message) {
        super(message);
        this.errorResponse = new ErrorResponse(
                ErrorMessage.builder()
                        .error("usuario_already_exists")
                        .description(message)
                        .build()
        );
    }
}
