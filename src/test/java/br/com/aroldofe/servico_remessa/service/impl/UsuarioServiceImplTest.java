package br.com.aroldofe.servico_remessa.service.impl;

import br.com.aroldofe.servico_remessa.api.bo.UsuarioBO;
import br.com.aroldofe.servico_remessa.domain.Usuario;
import br.com.aroldofe.servico_remessa.enums.TipoUsuario;
import br.com.aroldofe.servico_remessa.exception.UsuarioAlreadyExists;
import br.com.aroldofe.servico_remessa.repository.impl.UsuarioRepositoryImpl;
import br.com.aroldofe.servico_remessa.utils.anotation_test_category.UnitTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jpa.domain.Specification;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@UnitTest
@ExtendWith(MockitoExtension.class)
class UsuarioServiceImplTest {
    @Mock
    private UsuarioRepositoryImpl repository;

    private UsuarioServiceImpl service;

    @BeforeEach
    void setUp() {
        service = new UsuarioServiceImpl(repository);
    }

    @Test
    void create_ShouldThrowUsuarioAlreadyExists_WhenUsuarioWithSameCpfCnpjOrEmailExists() {
        final var usuarioBO = UsuarioBO.builder()
                .cpfCnpj("111.111.111-11")
                .email("teste@teste.com")
                .build();

        Mockito.doReturn(true)
                .when(repository)
                .exists(Mockito.any(Specification.class));

        final var exception = assertThrows(UsuarioAlreadyExists.class, () -> service.create(usuarioBO));

        assertEquals("Já existe um usuário com o mesmo CPF/CNPJ ou email.", exception.getMessage());
    }

    @Test
    void create_ShouldCreateUsuario_WhenNoUsuarioWithSameCpfCnpjOrEmailExists() {
        final var usuarioBO = UsuarioBO.builder()
                .nome("João Silva")
                .cpfCnpj("111.111.111-11")
                .email("teste@teste.com")
                .tipoUsuario(TipoUsuario.PESSOA_FISICA)
                .build();

        Mockito.doReturn(false)
                .when(repository)
                .exists(Mockito.any(Specification.class));

        Mockito.when(repository.save(Mockito.any(Usuario.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        final var usuario = this.service.create(usuarioBO);
        assertEquals(usuarioBO.getNome(), usuario.getNome());
        assertEquals(usuarioBO.getCpfCnpj(), usuario.getCpfCnpj());
        assertEquals(usuarioBO.getEmail(), usuario.getEmail());
        assertEquals(usuarioBO.getTipoUsuario(), usuario.getTipoUsuario());
    }
}