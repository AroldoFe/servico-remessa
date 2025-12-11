package br.com.aroldofe.servico_remessa.repository.jpa;

import br.com.aroldofe.servico_remessa.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UsuarioJpaRepository extends JpaRepository<Usuario, Long>, JpaSpecificationExecutor<Usuario> {
}
