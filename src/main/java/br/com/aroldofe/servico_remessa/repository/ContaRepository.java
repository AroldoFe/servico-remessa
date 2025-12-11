package br.com.aroldofe.servico_remessa.repository;

import br.com.aroldofe.servico_remessa.domain.Conta;
import org.springframework.data.jpa.domain.Specification;

import java.util.Collection;
import java.util.Optional;

public interface ContaRepository {
    Optional<Conta> findById(Long id);

    Collection<Conta> findAllByUsuarioId(Long usuarioId);

    Conta save(Conta conta);

    boolean exists(Specification<Conta> specification);
}
