package br.com.aroldofe.servico_remessa.repository;

import br.com.aroldofe.servico_remessa.domain.Usuario;
import org.springframework.data.jpa.domain.Specification;

import java.util.Optional;

public interface UsuarioRepository {
    Usuario save(Usuario usuario);

    Optional<Usuario> findById(Long id);

    boolean exists(Specification<Usuario> specification);
}
