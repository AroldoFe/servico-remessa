package br.com.aroldofe.servico_remessa.repository.impl;

import br.com.aroldofe.servico_remessa.domain.Conta;
import br.com.aroldofe.servico_remessa.repository.ContaRepository;
import br.com.aroldofe.servico_remessa.repository.jpa.ContaJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ContaRepositoryImpl implements ContaRepository {
    private final ContaJpaRepository repository;

    @Override
    public Optional<Conta> findById(Long id) {
        return this.repository.findById(id);
    }

    @Override
    public Collection<Conta> findAllByUsuarioId(Long usuarioId) {
        return this.repository.findAllByUsuarioId(usuarioId);
    }

    @Override
    public Conta save(Conta conta) {
        return this.repository.save(conta);
    }

    @Override
    public boolean exists(Specification<Conta> specification) {
        return this.repository.exists(specification);
    }
}
