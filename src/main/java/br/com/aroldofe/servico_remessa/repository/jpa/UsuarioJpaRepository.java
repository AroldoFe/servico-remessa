package br.com.aroldofe.servico_remessa.repository.jpa;

import br.com.aroldofe.servico_remessa.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioJpaRepository extends JpaRepository<Usuario, Long> {
}
