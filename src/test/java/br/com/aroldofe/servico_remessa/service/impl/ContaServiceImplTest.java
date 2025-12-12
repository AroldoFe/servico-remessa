package br.com.aroldofe.servico_remessa.service.impl;

import br.com.aroldofe.servico_remessa.api.bo.ContaBO;
import br.com.aroldofe.servico_remessa.domain.Usuario;
import br.com.aroldofe.servico_remessa.enums.TipoSaldoConta;
import br.com.aroldofe.servico_remessa.repository.ContaRepository;
import br.com.aroldofe.servico_remessa.repository.UsuarioRepository;
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
class ContaServiceImplTest {
    @Mock
    private ContaRepository contaRepository;

    @Mock
    private UsuarioRepository usuarioRepository;

    private ContaServiceImpl service;

    @BeforeEach
    void setUp() {
        service = new ContaServiceImpl(contaRepository, usuarioRepository);
    }

    @Test
    void create_ShouldThrowNotFoundException_WhenUsuarioDoesNotExist() {
        final var contaBO = ContaBO.builder()
                .usuarioId(1L)
                .numeroConta("010203")
                .tipoSaldoConta(TipoSaldoConta.BRL)
                .saldo(BigDecimal.ZERO)
                .build();

        Mockito.doReturn(Optional.empty())
                .when(this.usuarioRepository)
                .findById(Mockito.anyLong());

        final var exception = assertThrows(RuntimeException.class, () -> service.create(contaBO));

        assertEquals("Usuário não encontrado.", exception.getMessage());
    }

    @Test
    void create_ShouldThrowContaAlreadyExistsException_WhenContaWithSameTipoSaldoContaAndUsuarioExists() {
        final var contaBO = ContaBO.builder()
                .usuarioId(1L)
                .numeroConta("010203")
                .tipoSaldoConta(TipoSaldoConta.BRL)
                .saldo(BigDecimal.ZERO)
                .build();

        final var usuario = Usuario.builder()
                .id(1L)
                .build();

        Mockito.doReturn(Optional.ofNullable(usuario))
                .when(this.usuarioRepository)
                .findById(Mockito.anyLong());

        Mockito.doReturn(true)
                .when(this.contaRepository)
                .exists(Mockito.any());

        final var exception = assertThrows(RuntimeException.class, () -> service.create(contaBO));

        assertEquals("Já existe uma conta para este tipo de saldo e usuário.", exception.getMessage());
    }

    @Test
    void create_ShouldThrowContaAlreadyExistsException_WhenContaWithSameNumeroContaExists() {
        final var contaBO = ContaBO.builder()
                .usuarioId(1L)
                .numeroConta("010203")
                .tipoSaldoConta(TipoSaldoConta.BRL)
                .saldo(BigDecimal.ZERO)
                .build();

        final var usuario = Usuario.builder()
                .id(1L)
                .build();

        Mockito.doReturn(Optional.ofNullable(usuario))
                .when(this.usuarioRepository)
                .findById(Mockito.anyLong());

        Mockito.when(this.contaRepository.exists(Mockito.any()))
                .thenReturn(false, true);

        final var exception = assertThrows(RuntimeException.class, () -> service.create(contaBO));
        assertEquals("Já existe uma conta com este número.", exception.getMessage());
    }

    @Test
    void create_ShouldCreateConta_WhenNoConflictsExist() {
        final var contaBO = ContaBO.builder()
                .usuarioId(1L)
                .numeroConta("010203")
                .tipoSaldoConta(TipoSaldoConta.BRL)
                .saldo(BigDecimal.ZERO)
                .build();

        final var usuario = Usuario.builder()
                .id(1L)
                .build();

        Mockito.doReturn(Optional.ofNullable(usuario))
                .when(this.usuarioRepository)
                .findById(Mockito.anyLong());

        Mockito.when(this.contaRepository.exists(Mockito.any()))
                .thenReturn(false);

        Mockito.when(this.contaRepository.save(Mockito.any()))
                .thenAnswer(invocation -> invocation.getArgument(0));

        final var conta = this.service.create(contaBO);
        assertEquals(contaBO.getUsuarioId(), conta.getUsuarioId());
        assertEquals(contaBO.getNumeroConta(), conta.getNumeroConta());
        assertEquals(contaBO.getTipoSaldoConta(), conta.getTipoSaldoConta());
        assertEquals(BigDecimal.ZERO, conta.getSaldo());
    }
}