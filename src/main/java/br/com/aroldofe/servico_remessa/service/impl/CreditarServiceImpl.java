package br.com.aroldofe.servico_remessa.service.impl;

import br.com.aroldofe.servico_remessa.domain.OperacaoConta;
import br.com.aroldofe.servico_remessa.enums.TipoOperacao;
import br.com.aroldofe.servico_remessa.exception.NotFoundException;
import br.com.aroldofe.servico_remessa.repository.ContaRepository;
import br.com.aroldofe.servico_remessa.repository.OperacaoContaRepository;
import br.com.aroldofe.servico_remessa.service.CreditarService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CreditarServiceImpl implements CreditarService {
    private final OperacaoContaRepository operacaoContaRepository;
    private final ContaRepository contaRepository;

    @Override
    @Transactional
    public void credit(Long contaId, BigDecimal valor) {
        final var conta = this.contaRepository.findById(contaId)
                .orElseThrow(() -> new NotFoundException("Conta não encontrada."));

        final var operacaoConta = OperacaoConta.builder()
                .conta(conta)
                .descricao("Crédito de " + valor)
                .tipoOperacao(TipoOperacao.CREDITO)
                .valor(valor)
                .build();

        this.operacaoContaRepository.save(operacaoConta);

        conta.setSaldo(conta.getSaldo().add(valor));
        this.contaRepository.save(conta);
    }
}
