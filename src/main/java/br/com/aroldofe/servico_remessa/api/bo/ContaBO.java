package br.com.aroldofe.servico_remessa.api.bo;

import br.com.aroldofe.servico_remessa.enums.TipoSaldoConta;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContaBO {
    private Long id;
    private Long usuarioId;
    private String numeroConta;
    private TipoSaldoConta tipoSaldoConta;
    private BigDecimal saldo;
}
