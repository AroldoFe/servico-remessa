package br.com.aroldofe.servico_remessa.api.bo;

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
public class UsuarioBO {
    private Long id;
    private String nome;
    private String email;
    private TipoUsuario tipoUsuario;
    private String cpfCnpj;
}
