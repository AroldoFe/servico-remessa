package br.com.aroldofe.servico_remessa.service.impl;

import br.com.aroldofe.servico_remessa.domain.Conta;
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
class CreditarServiceImplTest {
    @Mock
    private ContaRepository contaRepository;

    @Mock
    private OperacaoContaRepository operacaoContaRepository;

    private CreditarServiceImpl service;

    @BeforeEach
    void setUp() {
        this.service = new CreditarServiceImpl(operacaoContaRepository, contaRepository);
    }

    @Test
    void credit_ShouldThrowNotFoundException_WhenContaDoesNotExist() {
        final var contaId = 1L;
        Mockito.doReturn(Optional.empty())
                .when(this.contaRepository)
                .findById(contaId);

        final var exception = assertThrows(NotFoundException.class, () -> this.service.credit(contaId, BigDecimal.TEN));
        assertEquals("Conta não encontrada.", exception.getMessage());
    }

    @Test
    void credit_ShouldCreditSuccessfully_WhenContaExists() {
        final var contaId = 1L;
        final var initialSaldo = BigDecimal.valueOf(100);
        final var creditAmount = BigDecimal.valueOf(50);
        final var expectedSaldo = initialSaldo.add(creditAmount);

        final var conta = Mockito.mock(Conta.class);
        Mockito.doReturn(Optional.of(conta))
                .when(this.contaRepository)
                .findById(contaId);
        Mockito.doReturn(initialSaldo).when(conta).getSaldo();

        this.service.credit(contaId, creditAmount);

        Mockito.verify(operacaoContaRepository).save(Mockito.any());
        Mockito.verify(conta).setSaldo(expectedSaldo);
        Mockito.verify(contaRepository).save(conta);
    }
}