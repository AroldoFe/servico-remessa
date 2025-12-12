package br.com.aroldofe.servico_remessa.controller;

import br.com.aroldofe.servico_remessa.api.bo.ContaBO;
import br.com.aroldofe.servico_remessa.api.mapper.ContaMapper;
import br.com.aroldofe.servico_remessa.api.request.CreateContaRequest;
import br.com.aroldofe.servico_remessa.api.request.CreditRequest;
import br.com.aroldofe.servico_remessa.api.request.DebitRequest;
import br.com.aroldofe.servico_remessa.api.response.ContaResponse;
import br.com.aroldofe.servico_remessa.enums.TipoSaldoConta;
import br.com.aroldofe.servico_remessa.service.ContaService;
import br.com.aroldofe.servico_remessa.service.CreditarService;
import br.com.aroldofe.servico_remessa.service.DebitarService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    private final ContaService contaService;
    private final CreditarService creditarService;
    private final DebitarService debitarService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ContaResponse create(@RequestBody @Valid CreateContaRequest request) {
        final var contaBO = ContaBO.builder()
                .usuarioId(request.getUsuarioId())
                .numeroConta(request.getNumeroConta())
                .tipoSaldoConta(TipoSaldoConta.valueOf(request.getTipoSaldoConta()))
                .build();
        final var conta = contaService.create(contaBO);

        return ContaMapper.toResponse(conta);
    }

    @PatchMapping("/{id}/creditar")
    @ResponseStatus(HttpStatus.OK)
    public ContaResponse credit(@PathVariable Long id, @RequestBody @Valid CreditRequest request) {
        this.creditarService.credit(id, request.getValor());
        final var conta = this.contaService.findById(id).get();
        return ContaMapper.toResponse(conta);
    }

    @PatchMapping("/{id}/debitar")
    @ResponseStatus(HttpStatus.OK)
    public ContaResponse debit(@PathVariable Long id, @RequestBody @Valid DebitRequest request) {
        this.debitarService.debit(id, request.getValor());
        final var conta = this.contaService.findById(id).get();
        return ContaMapper.toResponse(conta);
    }
}
