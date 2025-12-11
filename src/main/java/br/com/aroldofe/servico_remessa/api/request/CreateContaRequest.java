package br.com.aroldofe.servico_remessa.api.request;

import br.com.aroldofe.servico_remessa.enums.TipoSaldoConta;
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
    private Long usuarioId;
    private String numeroConta;
    private TipoSaldoConta tipoSaldoConta;
}
