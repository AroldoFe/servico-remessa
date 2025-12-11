package br.com.aroldofe.servico_remessa.controller;

import br.com.aroldofe.servico_remessa.api.bo.ContaBO;
import br.com.aroldofe.servico_remessa.api.request.CreateContaRequest;
import br.com.aroldofe.servico_remessa.definition.AbstractContractTest;
import br.com.aroldofe.servico_remessa.enums.TipoSaldoConta;
import br.com.aroldofe.servico_remessa.exception.ErrorMessage;
import br.com.aroldofe.servico_remessa.exception.ErrorResponse;
import br.com.aroldofe.servico_remessa.service.impl.ContaServiceImpl;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import tools.jackson.databind.ObjectMapper;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ContaRestController.class)
class ContaRestControllerContractTest extends AbstractContractTest {
    @MockitoBean
    private ContaServiceImpl service;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    @SneakyThrows
    void shouldReturn_BadRequest_WhenUsuarioIdIsNull() {
        final var requestBody = CreateContaRequest.builder()
                .numeroConta("010203-4")
                .tipoSaldoConta(TipoSaldoConta.BRL.name())
                .build();

        final var expectedError = new ErrorResponse(
                ErrorMessage.builder()
                        .error("validation_error")
                        .parameterName("usuarioId")
                        .description("O ID do usuário é obrigatório.")
                        .build()
        );

        mockMvc.perform(
                        MockMvcRequestBuilders.post("/contas")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(requestBody))
                ).andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(expectedError)));
    }

    @Test
    @SneakyThrows
    void shouldReturn_BadRequest_WhenNumeroContaIsNull() {
        final var requestBody = CreateContaRequest.builder()
                .usuarioId(1L)
                .tipoSaldoConta(TipoSaldoConta.BRL.name())
                .build();

        final var expectedError = new ErrorResponse(
                ErrorMessage.builder()
                        .error("validation_error")
                        .parameterName("numeroConta")
                        .description("O número da conta é obrigatório.")
                        .build()
        );

        mockMvc.perform(
                        MockMvcRequestBuilders.post("/contas")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(requestBody))
                ).andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(expectedError)));
    }

    @Test
    @SneakyThrows
    void shouldReturn_BadRequest_WhenNumeroContaIsBlank() {
        final var requestBody = CreateContaRequest.builder()
                .usuarioId(1L)
                .numeroConta("   ")
                .tipoSaldoConta(TipoSaldoConta.BRL.name())
                .build();

        final var expectedError = new ErrorResponse(
                ErrorMessage.builder()
                        .error("validation_error")
                        .parameterName("numeroConta")
                        .description("O número da conta é obrigatório.")
                        .build()
        );

        mockMvc.perform(
                        MockMvcRequestBuilders.post("/contas")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(requestBody))
                ).andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(expectedError)));
    }

    @Test
    @SneakyThrows
    void shouldReturn_BadRequest_WhenTipoSaldoContaIsNull() {
        final var requestBody = CreateContaRequest.builder()
                .usuarioId(1L)
                .numeroConta("010203-4")
                .build();

        final var expectedError = new ErrorResponse(
                ErrorMessage.builder()
                        .error("validation_error")
                        .parameterName("tipoSaldoConta")
                        .description("O tipo de saldo da conta é obrigatório.")
                        .build()
        );

        mockMvc.perform(
                        MockMvcRequestBuilders.post("/contas")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(requestBody))
                ).andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(expectedError)));
    }

    @Test
    @SneakyThrows
    void shouldReturn_BadRequest_WhenTipoSaldoContaIsBlank() {
        final var requestBody = CreateContaRequest.builder()
                .usuarioId(1L)
                .numeroConta("010203-4")
                .tipoSaldoConta("")
                .build();

        final var expectedError = new ErrorResponse(
                ErrorMessage.builder()
                        .error("validation_error")
                        .parameterName("tipoSaldoConta")
                        .description("Tipo de saldo da conta inválido.")
                        .build()
        );

        mockMvc.perform(
                        MockMvcRequestBuilders.post("/contas")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(requestBody))
                ).andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(expectedError)));
    }

    @Test
    @SneakyThrows
    void shouldReturn_BadRequest_WhenTipoSaldoContaIsInvalid() {
        final var requestBody = CreateContaRequest.builder()
                .usuarioId(1L)
                .numeroConta("010203-4")
                .tipoSaldoConta("INVALID")
                .build();

        final var expectedError = new ErrorResponse(
                ErrorMessage.builder()
                        .error("validation_error")
                        .parameterName("tipoSaldoConta")
                        .description("Tipo de saldo da conta inválido.")
                        .build()
        );

        mockMvc.perform(
                        MockMvcRequestBuilders.post("/contas")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(requestBody))
                ).andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(expectedError)));
    }

    @Test
    @SneakyThrows
    void shouldReturn_Created_When_requestIsValidWithCnpj_formatted() {
        final var requestBody = CreateContaRequest.builder()
                .usuarioId(1L)
                .numeroConta("010203-4")
                .tipoSaldoConta(TipoSaldoConta.BRL.name())
                .build();

        final var contaBO = ContaBO.builder()
                .id(1L)
                .usuarioId(requestBody.getUsuarioId())
                .numeroConta(requestBody.getNumeroConta())
                .tipoSaldoConta(TipoSaldoConta.valueOf(requestBody.getTipoSaldoConta()))
                .saldo(BigDecimal.ZERO)
                .build();

        Mockito.doReturn(contaBO)
                .when(service)
                .create(Mockito.any(ContaBO.class));

        mockMvc.perform(
                MockMvcRequestBuilders.post("/contas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestBody))
        ).andExpect(status().isCreated());
    }

}

