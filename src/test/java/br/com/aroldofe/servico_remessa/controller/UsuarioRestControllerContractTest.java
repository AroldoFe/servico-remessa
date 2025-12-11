package br.com.aroldofe.servico_remessa.controller;

import br.com.aroldofe.servico_remessa.api.bo.UsuarioBO;
import br.com.aroldofe.servico_remessa.api.mapper.UsuarioMapper;
import br.com.aroldofe.servico_remessa.api.request.CreateUsuarioRequest;
import br.com.aroldofe.servico_remessa.definition.AbstractContractTest;
import br.com.aroldofe.servico_remessa.exception.ErrorMessage;
import br.com.aroldofe.servico_remessa.exception.ErrorResponse;
import br.com.aroldofe.servico_remessa.service.impl.UsuarioServiceImpl;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import tools.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = UsuarioRestController.class)
class UsuarioRestControllerContractTest extends AbstractContractTest {
    @MockitoBean
    private UsuarioServiceImpl usuarioService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private final UsuarioMapper usuarioMapper = new UsuarioMapper();

    // Testes para validação de nome
    @Test
    @SneakyThrows
    void shouldReturn_BadRequest_WhenNameIsBlank() {
        final var requestBody = CreateUsuarioRequest.builder()
                .nome("")
                .email("teste@teste.com")
                .cpfCnpj("111.111.111-11")
                .build();

        final var expectedError = new ErrorResponse(
                ErrorMessage.builder()
                        .error("validation_error")
                        .parameterName("nome")
                        .description("O nome não pode ser vazio.")
                        .build()
        );

        mockMvc.perform(
                        MockMvcRequestBuilders.post("/usuarios")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(requestBody))
                ).andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(expectedError)));
    }

    @Test
    @SneakyThrows
    void shouldReturn_BadRequest_WhenNameIsNull() {
        final var requestBody = CreateUsuarioRequest.builder()
                .email("teste@teste.com")
                .cpfCnpj("111.111.111-11")
                .build();

        final var expectedError = new ErrorResponse(
                ErrorMessage.builder()
                        .error("validation_error")
                        .parameterName("nome")
                        .description("O nome não pode ser vazio.")
                        .build()
        );

        mockMvc.perform(
                        MockMvcRequestBuilders.post("/usuarios")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(requestBody))
                ).andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(expectedError)));
    }

    @Test
    @SneakyThrows
    void shouldReturn_BadRequest_WhenNameExceedsMaxSize() {
        final var requestBody = CreateUsuarioRequest.builder()
                .nome("a".repeat(256))
                .email("teste@teste.com")
                .cpfCnpj("111.111.111-11")
                .build();

        final var expectedError = new ErrorResponse(
                ErrorMessage.builder()
                        .error("validation_error")
                        .parameterName("nome")
                        .description("O nome deve ter no máximo 255 caracteres.")
                        .build()
        );

        mockMvc.perform(
                        MockMvcRequestBuilders.post("/usuarios")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(requestBody))
                ).andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(expectedError)));
    }

    // Testes para validação de email
    @Test
    @SneakyThrows
    void shouldReturn_BadRequest_WhenEmailIsBlank() {
        final var requestBody = CreateUsuarioRequest.builder()
                .nome("João Silva")
                .email("")
                .cpfCnpj("111.111.111-11")
                .build();

        final var expectedError = new ErrorResponse(
                ErrorMessage.builder()
                        .error("validation_error")
                        .parameterName("email")
                        .description("O email não pode ser vazio.")
                        .build()
        );

        mockMvc.perform(
                        MockMvcRequestBuilders.post("/usuarios")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(requestBody))
                ).andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(expectedError)));
    }

    @Test
    @SneakyThrows
    void shouldReturn_BadRequest_WhenEmailIsNull() {
        final var requestBody = CreateUsuarioRequest.builder()
                .nome("João Silva")
                .cpfCnpj("111.111.111-11")
                .build();

        final var expectedError = new ErrorResponse(
                ErrorMessage.builder()
                        .error("validation_error")
                        .parameterName("email")
                        .description("O email não pode ser vazio.")
                        .build()
        );

        mockMvc.perform(
                        MockMvcRequestBuilders.post("/usuarios")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(requestBody))
                ).andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(expectedError)));
    }

    @Test
    @SneakyThrows
    void shouldReturn_BadRequest_WhenEmailIsInvalid() {
        final var requestBody = CreateUsuarioRequest.builder()
                .nome("João Silva")
                .email("email-invalido")
                .cpfCnpj("111.111.111-11")
                .build();

        final var expectedError = new ErrorResponse(
                ErrorMessage.builder()
                        .error("validation_error")
                        .parameterName("email")
                        .description("O email deve ser válido.")
                        .build()
        );

        mockMvc.perform(
                        MockMvcRequestBuilders.post("/usuarios")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(requestBody))
                ).andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(expectedError)));
    }

    @Test
    @SneakyThrows
    void shouldReturn_BadRequest_WhenEmailExceedsMaxSize() {
        final var requestBody = CreateUsuarioRequest.builder()
                .nome("João Silva")
                .email("a".repeat(95) + "@teste.com")
                .cpfCnpj("111.111.111-11")
                .build();

        final var expectedError = new ErrorResponse(
                ErrorMessage.builder()
                        .error("validation_error")
                        .parameterName("email")
                        .description("O email deve ter no máximo 100 caracteres.")
                        .build(),
                ErrorMessage.builder()
                        .error("validation_error")
                        .parameterName("email")
                        .description("O email deve ser válido.")
                        .build()
        );

        mockMvc.perform(
                        MockMvcRequestBuilders.post("/usuarios")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(requestBody))
                ).andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(expectedError)));
    }

    // Testes para validação de CPF/CNPJ
    @Test
    @SneakyThrows
    void shouldReturn_BadRequest_WhenCpfCnpjIsNull() {
        final var requestBody = CreateUsuarioRequest.builder()
                .nome("João Silva")
                .email("teste@teste.com")
                .build();

        final var expectedError = new ErrorResponse(
                ErrorMessage.builder()
                        .error("validation_error")
                        .parameterName("cpfCnpj")
                        .description("O CPF/CNPJ não pode ser nulo.")
                        .build()
        );

        mockMvc.perform(
                        MockMvcRequestBuilders.post("/usuarios")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(requestBody))
                ).andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(expectedError)));
    }

    @Test
    @SneakyThrows
    void shouldReturn_BadRequest_WhenCpfCnpjIsInvalid() {
        final var requestBody = CreateUsuarioRequest.builder()
                .nome("João Silva")
                .email("teste@teste.com")
                .cpfCnpj("123456789")
                .build();

        final var expectedError = new ErrorResponse(
                ErrorMessage.builder()
                        .error("validation_error")
                        .parameterName("cpfCnpj")
                        .description("O CPF/CNPJ deve ser válido.")
                        .build()
        );

        mockMvc.perform(
                        MockMvcRequestBuilders.post("/usuarios")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(requestBody))
                ).andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(expectedError)));
    }

    // Testes para requisições válidas
    @Test
    @SneakyThrows
    void shouldReturn_Created_When_requestIsValidWithCpf_formatted() {
        final var requestBody = CreateUsuarioRequest.builder()
                .nome("João Silva")
                .email("teste@teste.com")
                .cpfCnpj("111.111.111-11")
                .build();

        Mockito.doReturn(usuarioMapper.toBO(requestBody))
                .when(usuarioService)
                .criarUsuario(Mockito.any(UsuarioBO.class));

        mockMvc.perform(
                MockMvcRequestBuilders.post("/usuarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestBody))
        ).andExpect(status().isCreated());
    }

 /*   @Test
    @SneakyThrows
    void shouldReturn_Ok_When_requestIsValidWithCpf_unformatted() {
        final var requestBody = CreateUsuarioRequest.builder()
                .nome("João Silva")
                .email("teste@teste.com")
                .cpfCnpj("11111111111")
                .build();

        mockMvc.perform(
                MockMvcRequestBuilders.post("/usuarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestBody))
        ).andExpect(status().isOk());
    }*/

    @Test
    @SneakyThrows
    void shouldReturn_Created_When_requestIsValidWithCnpj_formatted() {
        final var requestBody = CreateUsuarioRequest.builder()
                .nome("Empresa LTDA")
                .email("empresa@teste.com")
                .cpfCnpj("11.111.111/1111-11")
                .build();

        Mockito.doReturn(usuarioMapper.toBO(requestBody))
                .when(usuarioService)
                .criarUsuario(Mockito.any(UsuarioBO.class));

        mockMvc.perform(
                MockMvcRequestBuilders.post("/usuarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestBody))
        ).andExpect(status().isCreated());
    }

    /*@Test
    @SneakyThrows
    void shouldReturn_Ok_When_requestIsValidWithCnpj_unformatted() {
        final var requestBody = CreateUsuarioRequest.builder()
                .nome("Empresa LTDA")
                .email("empresa@teste.com")
                .cpfCnpj("11111111111111")
                .build();

        mockMvc.perform(
                MockMvcRequestBuilders.post("/usuarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestBody))
        ).andExpect(status().isOk());
    }*/
}

