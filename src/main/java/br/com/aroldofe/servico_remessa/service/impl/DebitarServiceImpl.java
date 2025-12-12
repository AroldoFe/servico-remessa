package br.com.aroldofe.servico_remessa.service.impl;

import br.com.aroldofe.servico_remessa.domain.OperacaoConta;
import br.com.aroldofe.servico_remessa.enums.TipoOperacao;
import br.com.aroldofe.servico_remessa.exception.InsufficientFundsException;
import br.com.aroldofe.servico_remessa.exception.NotFoundException;
import br.com.aroldofe.servico_remessa.repository.ContaRepository;
import br.com.aroldofe.servico_remessa.repository.OperacaoContaRepository;
import br.com.aroldofe.servico_remessa.service.DebitarService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DebitarServiceImpl implements DebitarService {
    private final OperacaoContaRepository operacaoContaRepository;
    private final ContaRepository contaRepository;

    @Override
    @Transactional
    public void debit(Long contaId, BigDecimal valor) {
        final var conta = this.contaRepository.findById(contaId)
                .orElseThrow(() -> new NotFoundException("Conta não encontrada."));

        if (conta.getSaldo().compareTo(valor) < 0) {
            throw new InsufficientFundsException("Saldo insuficiente para débito.");
        }

        final var operacaoConta = OperacaoConta.builder()
                .conta(conta)
                .descricao("Débito de " + valor)
                .tipoOperacao(TipoOperacao.DEBITO)
                .valor(valor)
                .build();

        this.operacaoContaRepository.save(operacaoConta);

        conta.setSaldo(conta.getSaldo().subtract(valor));
        this.contaRepository.save(conta);
    }
}
