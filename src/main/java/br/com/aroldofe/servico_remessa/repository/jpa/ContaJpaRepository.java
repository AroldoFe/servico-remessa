package br.com.aroldofe.servico_remessa.repository.jpa;

import br.com.aroldofe.servico_remessa.domain.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Collection;

public interface ContaJpaRepository extends JpaRepository<Conta, Long>, JpaSpecificationExecutor<Conta> {
    Collection<Conta> findAllByUsuarioId(Long usuarioId);
}
