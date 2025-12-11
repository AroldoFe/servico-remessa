package br.com.aroldofe.servico_remessa.api.mapper;

import br.com.aroldofe.servico_remessa.api.bo.UsuarioBO;
import br.com.aroldofe.servico_remessa.api.request.CreateUsuarioRequest;
import br.com.aroldofe.servico_remessa.api.response.UsuarioResponse;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UsuarioMapper {
    public UsuarioBO toBO(UsuarioResponse response) {
        return UsuarioBO.builder()
                .id(response.getId())
                .nome(response.getNome())
                .email(response.getEmail())
                .tipoUsuario(response.getTipoUsuario())
                .cpfCnpj(response.getCpfCnpj())
                .build();
    }

    public UsuarioBO toBO(CreateUsuarioRequest request) {
        return UsuarioBO.builder()
                .nome(request.getNome())
                .email(request.getEmail())
                .tipoUsuario(request.getTipoUsuario())
                .cpfCnpj(request.getCpfCnpj())
                .build();
    }

    public UsuarioResponse toResponse(UsuarioBO bo) {
        return UsuarioResponse.builder()
                .id(bo.getId())
                .nome(bo.getNome())
                .email(bo.getEmail())
                .tipoUsuario(bo.getTipoUsuario())
                .cpfCnpj(bo.getCpfCnpj())
                .build();
    }
}
