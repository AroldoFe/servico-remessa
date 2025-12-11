package br.com.aroldofe.servico_remessa.api.request;

import br.com.aroldofe.servico_remessa.enums.TipoUsuario;
import br.com.aroldofe.servico_remessa.utils.Constants;
import br.com.aroldofe.servico_remessa.validation.CpfCnpj;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
public class CreateUsuarioRequest {
    @NotBlank(message = "O nome não pode ser vazio.")
    @Size(max = 255, message = "O nome deve ter no máximo {max} caracteres.")
    private String nome;

    @NotBlank(message = "O email não pode ser vazio.")
    @Email(message = "O email deve ser válido.")
    @Size(max = 100, message = "O email deve ter no máximo {max} caracteres.")
    private String email;

    @NotNull(message = "O CPF/CNPJ não pode ser nulo.")
    @CpfCnpj(message = "O CPF/CNPJ deve ser válido.")
    private String cpfCnpj;

    @JsonIgnore
    public TipoUsuario getTipoUsuario() {
        if (this.cpfCnpj.matches(Constants.CPF_REGEX)) {
            return TipoUsuario.PESSOA_FISICA;
        } else {
            return TipoUsuario.PESSOA_JURIDICA;
        }
    }
}
