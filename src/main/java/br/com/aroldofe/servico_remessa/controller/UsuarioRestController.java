package br.com.aroldofe.servico_remessa.controller;

import br.com.aroldofe.servico_remessa.api.mapper.UsuarioMapper;
import br.com.aroldofe.servico_remessa.api.request.CreateUsuarioRequest;
import br.com.aroldofe.servico_remessa.api.response.UsuarioResponse;
import br.com.aroldofe.servico_remessa.service.UsuarioService;
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
@RequestMapping("/usuarios")
@RequiredArgsConstructor
@Validated
public class UsuarioRestController {
    private final UsuarioService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioResponse create(
            @RequestBody @Valid CreateUsuarioRequest request
    ) {
        final var usuario = service.create(UsuarioMapper.toBO(request));
        return UsuarioMapper.toResponse(usuario);
    }
}
