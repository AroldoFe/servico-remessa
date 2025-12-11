package br.com.aroldofe.servico_remessa.repository.specification;

import br.com.aroldofe.servico_remessa.domain.Conta;
import br.com.aroldofe.servico_remessa.enums.TipoSaldoConta;
import org.springframework.data.jpa.domain.Specification;

public class ContaSpecification {
    public static Specification<Conta> hasUsuarioId(Long usuarioId) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("usuario").get("id"), usuarioId);
    }

    public static Specification<Conta> hasNumeroConta(String numeroConta) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("numeroConta"), numeroConta);
    }

    public static Specification<Conta> hasTipoSaldoConta(TipoSaldoConta tipoSaldoConta) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("tipoSaldoConta"), tipoSaldoConta);
    }
}
