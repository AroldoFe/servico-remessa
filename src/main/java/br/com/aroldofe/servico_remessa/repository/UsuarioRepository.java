package br.com.aroldofe.servico_remessa.repository;

import br.com.aroldofe.servico_remessa.domain.Usuario;

import java.util.Optional;

public interface UsuarioRepository {
    Usuario save(Usuario usuario);
}
