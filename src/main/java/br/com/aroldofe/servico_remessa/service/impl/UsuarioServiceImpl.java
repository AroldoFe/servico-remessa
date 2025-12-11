package br.com.aroldofe.servico_remessa.service.impl;

import br.com.aroldofe.servico_remessa.api.bo.UsuarioBO;
import br.com.aroldofe.servico_remessa.api.mapper.UsuarioMapper;
import br.com.aroldofe.servico_remessa.exception.UsuarioAlreadyExists;
import br.com.aroldofe.servico_remessa.repository.UsuarioRepository;
import br.com.aroldofe.servico_remessa.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static br.com.aroldofe.servico_remessa.repository.specification.UsuarioSpecification.findByCpfCnpj;
import static br.com.aroldofe.servico_remessa.repository.specification.UsuarioSpecification.findByEmail;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UsuarioServiceImpl implements UsuarioService {
    private final UsuarioRepository repository;

    @Override
    @Transactional
    public UsuarioBO create(UsuarioBO usuarioBO) {
        final var existsUsuarioParecido = repository.exists(
                findByCpfCnpj(usuarioBO.getCpfCnpj())
                        .or(findByEmail(usuarioBO.getEmail()))
        );

        if (existsUsuarioParecido) {
            throw new UsuarioAlreadyExists("Já existe um usuário com o mesmo CPF/CNPJ ou email.");
        }

        var usuario = UsuarioMapper.toEntity(usuarioBO);
        usuario = repository.save(usuario);
        return UsuarioMapper.toBO(usuario);
    }
}
