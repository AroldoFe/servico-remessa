package br.com.aroldofe.servico_remessa.domain;

import br.com.aroldofe.servico_remessa.enums.TipoUsuario;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Version;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Usuario {
    @Id
    @GeneratedValue
    private Long id;

    @Version
    private Long version = 0L;

    @Column(nullable = false, length = 255)
    private String nome;

    @Column(unique = true, nullable = false, length = 100)
    private String email;

    @Column(nullable = false, length = 30)
    @Enumerated(EnumType.STRING)
    private TipoUsuario tipoUsuario;

    @Column(unique = true, nullable = false, length = 30)
    private String cpfCnpj;
}
