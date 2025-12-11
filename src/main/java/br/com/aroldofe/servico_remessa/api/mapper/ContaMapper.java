package br.com.aroldofe.servico_remessa.api.mapper;

import br.com.aroldofe.servico_remessa.api.bo.ContaBO;
import br.com.aroldofe.servico_remessa.api.response.ContaResponse;
import br.com.aroldofe.servico_remessa.domain.Conta;
import br.com.aroldofe.servico_remessa.domain.Usuario;

public class ContaMapper {
    public static Conta toEntity(ContaBO contaBO) {
        if (contaBO == null) {
            return null;
        }

        final var usuario = Usuario.builder()
                .id(contaBO.getUsuarioId())
                .build();

        return Conta.builder()
                .id(contaBO.getId())
                .usuario(usuario)
                .numeroConta(contaBO.getNumeroConta())
                .tipoSaldoConta(contaBO.getTipoSaldoConta())
                .saldo(contaBO.getSaldo())
                .build();
    }

    public static ContaBO toBO(Conta conta) {
        if (conta == null) {
            return null;
        }

        return ContaBO.builder()
                .id(conta.getId())
                .usuarioId(conta.getUsuario().getId())
                .numeroConta(conta.getNumeroConta())
                .tipoSaldoConta(conta.getTipoSaldoConta())
                .saldo(conta.getSaldo())
                .build();
    }

    public static ContaResponse toResponse(ContaBO conta) {
        if (conta == null) {
            return null;
        }

        return ContaResponse.builder()
                .id(conta.getId())
                .usuarioId(conta.getUsuarioId())
                .numeroConta(conta.getNumeroConta())
                .tipoSaldoConta(conta.getTipoSaldoConta())
                .saldo(conta.getSaldo())
                .build();
    }
}
