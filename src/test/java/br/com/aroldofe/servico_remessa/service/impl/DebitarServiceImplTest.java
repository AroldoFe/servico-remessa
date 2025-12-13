package br.com.aroldofe.servico_remessa.service.impl;

import br.com.aroldofe.servico_remessa.exception.NotFoundException;
import br.com.aroldofe.servico_remessa.repository.ContaRepository;
import br.com.aroldofe.servico_remessa.repository.OperacaoContaRepository;
import br.com.aroldofe.servico_remessa.utils.anotation_test_category.UnitTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@UnitTest
@ExtendWith(MockitoExtension.class)
class DebitarServiceImplTest {
    @Mock
    private ContaRepository contaRepository;

    @Mock
    private OperacaoContaRepository operacaoContaRepository;

    private DebitarServiceImpl service;

    @BeforeEach
    void setUp() {
        this.service = new DebitarServiceImpl(operacaoContaRepository, contaRepository);
    }

    @Test
    void debit_ShouldThrowNotFoundException_WhenContaDoesNotExist() {
        // Implementar o teste
        final var contaId = 1L;
        Mockito.doReturn(Optional.empty())
                .when(this.contaRepository)
                .findById(contaId);

        final var exception = assertThrows(NotFoundException.class, () -> this.service.debit(contaId, BigDecimal.TEN));
        assertEquals("Conta não encontrada.", exception.getMessage());
    }

    @Test
    void debit_ShouldThrowInsufficientFundsException_WhenSaldoIsInsufficient() {
        final var contaId = 1L;
        final var debitAmount = BigDecimal.valueOf(150);
        final var initialSaldo = BigDecimal.valueOf(100);

        final var conta = Mockito.mock(br.com.aroldofe.servico_remessa.domain.Conta.class);
        Mockito.doReturn(Optional.of(conta))
                .when(this.contaRepository)
                .findById(contaId);
        Mockito.doReturn(initialSaldo).when(conta).getSaldo();

        final var exception = assertThrows(br.com.aroldofe.servico_remessa.exception.InsufficientFundsException.class, () -> this.service.debit(contaId, debitAmount));
        assertEquals("Saldo insuficiente para débito.", exception.getMessage());
    }

    @Test
    void debit_ShouldDebitSuccessfully_WhenContaExistsAndSaldoIsSufficient() {
        final var contaId = 1L;
        final var initialSaldo = BigDecimal.valueOf(200);
        final var debitAmount = BigDecimal.valueOf(50);
        final var expectedSaldo = initialSaldo.subtract(debitAmount);

        final var conta = Mockito.mock(br.com.aroldofe.servico_remessa.domain.Conta.class);
        Mockito.doReturn(Optional.of(conta))
                .when(this.contaRepository)
                .findById(contaId);
        Mockito.doReturn(initialSaldo).when(conta).getSaldo();

        this.service.debit(contaId, debitAmount);

        Mockito.verify(conta).setSaldo(expectedSaldo);
        Mockito.verify(this.contaRepository).save(conta);
    }
}