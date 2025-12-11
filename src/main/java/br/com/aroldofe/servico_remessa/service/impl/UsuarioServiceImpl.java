package br.com.aroldofe.servico_remessa.service.impl;

import br.com.aroldofe.servico_remessa.api.bo.UsuarioBO;
import br.com.aroldofe.servico_remessa.domain.Usuario;
import br.com.aroldofe.servico_remessa.repository.UsuarioRepository;
import br.com.aroldofe.servico_remessa.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UsuarioServiceImpl implements UsuarioService {
    private final UsuarioRepository repository;

    @Override
    @Transactional
    public UsuarioBO criarUsuario(UsuarioBO usuarioBO) {
        var usuario = Usuario.builder()
                .nome(usuarioBO.getNome())
                .email(usuarioBO.getEmail())
                .cpfCnpj(usuarioBO.getCpfCnpj())
                .tipoUsuario(usuarioBO.getTipoUsuario())
                .build();
        usuario = repository.save(usuario);
        return UsuarioBO.builder()
                .id(usuario.getId())
                .nome(usuario.getNome())
                .email(usuario.getEmail())
                .cpfCnpj(usuario.getCpfCnpj())
                .tipoUsuario(usuario.getTipoUsuario())
                .build();
    }
}
