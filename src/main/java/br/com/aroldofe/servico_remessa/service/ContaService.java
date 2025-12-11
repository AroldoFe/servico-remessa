package br.com.aroldofe.servico_remessa.service;

import br.com.aroldofe.servico_remessa.api.bo.ContaBO;

import java.util.Collection;
import java.util.Optional;

public interface ContaService {
    ContaBO create(ContaBO contaBO);

    Optional<ContaBO> findById(Long id);

    Collection<ContaBO> findByUsuarioId(Long usuarioId);
}
