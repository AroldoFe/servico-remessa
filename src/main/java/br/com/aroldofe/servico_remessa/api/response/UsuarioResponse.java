package br.com.aroldofe.servico_remessa.api.response;

import br.com.aroldofe.servico_remessa.enums.TipoUsuario;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioResponse {
    private Long id;
    private String nome;
    private String email;
    private TipoUsuario tipoUsuario;
    private String cpfCnpj;
}
