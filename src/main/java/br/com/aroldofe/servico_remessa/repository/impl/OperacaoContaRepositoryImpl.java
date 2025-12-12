package br.com.aroldofe.servico_remessa.repository.impl;

import br.com.aroldofe.servico_remessa.domain.OperacaoConta;
import br.com.aroldofe.servico_remessa.repository.OperacaoContaRepository;
import br.com.aroldofe.servico_remessa.repository.jpa.OperacaoContaJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OperacaoContaRepositoryImpl implements OperacaoContaRepository {
    private final OperacaoContaJpaRepository repository;

    @Override
    public OperacaoConta save(OperacaoConta operacaoConta) {
        return this.repository.save(operacaoConta);
    }
}
