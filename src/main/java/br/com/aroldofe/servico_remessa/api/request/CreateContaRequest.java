package br.com.aroldofe.servico_remessa.api.request;

import br.com.aroldofe.servico_remessa.enums.TipoSaldoConta;
import br.com.aroldofe.servico_remessa.validation.Enum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateContaRequest {
    @NotNull(message = "O ID do usuário é obrigatório.")
    private Long usuarioId;

    @NotBlank(message = "O número da conta é obrigatório.")
    private String numeroConta;

    @NotNull(message = "O tipo de saldo da conta é obrigatório.")
    @Enum(enumClass = TipoSaldoConta.class, message = "Tipo de saldo da conta inválido.")
    private String tipoSaldoConta;
}
