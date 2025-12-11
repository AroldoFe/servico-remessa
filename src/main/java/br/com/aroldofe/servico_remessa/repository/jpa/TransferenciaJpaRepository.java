package br.com.aroldofe.servico_remessa.repository.jpa;

import br.com.aroldofe.servico_remessa.domain.Transferencia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransferenciaJpaRepository extends JpaRepository<Transferencia, Long> {
}
