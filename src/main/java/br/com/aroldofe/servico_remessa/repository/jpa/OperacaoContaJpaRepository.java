package br.com.aroldofe.servico_remessa.repository.jpa;

import br.com.aroldofe.servico_remessa.domain.OperacaoConta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperacaoContaJpaRepository extends JpaRepository<OperacaoConta, Long> {
}
