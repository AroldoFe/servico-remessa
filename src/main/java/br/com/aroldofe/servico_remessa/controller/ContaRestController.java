package br.com.aroldofe.servico_remessa.controller;

import br.com.aroldofe.servico_remessa.api.bo.ContaBO;
import br.com.aroldofe.servico_remessa.api.mapper.ContaMapper;
import br.com.aroldofe.servico_remessa.api.request.CreateContaRequest;
import br.com.aroldofe.servico_remessa.api.response.ContaResponse;
import br.com.aroldofe.servico_remessa.service.ContaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/contas")
@RequiredArgsConstructor
@Validated
public class ContaRestController {
    private final ContaService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ContaResponse create(@RequestBody @Valid CreateContaRequest request) {
        final var contaBO = ContaBO.builder()
                .usuarioId(request.getUsuarioId())
                .numeroConta(request.getNumeroConta())
                .tipoSaldoConta(request.getTipoSaldoConta())
                .build();
        final var conta = service.create(contaBO);

        return ContaMapper.toResponse(conta);
    }
}
