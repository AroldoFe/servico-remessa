package br.com.aroldofe.servico_remessa.repository.impl;

import br.com.aroldofe.servico_remessa.domain.Usuario;
import br.com.aroldofe.servico_remessa.repository.UsuarioRepository;
import br.com.aroldofe.servico_remessa.repository.jpa.UsuarioJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class UsuarioRepositoryImpl implements UsuarioRepository {
    private final UsuarioJpaRepository repository;

    @Override
    public Usuario save(Usuario usuario) {
        return this.repository.save(usuario);
    }

    @Override
    public Optional<Usuario> findById(Long id) {
        return this.repository.findById(id);
    }

    @Override
    public boolean exists(Specification<Usuario> specification) {
        return this.repository.exists(specification);
    }
}
