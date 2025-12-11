package br.com.aroldofe.servico_remessa.domain;

import br.com.aroldofe.servico_remessa.enums.TipoSaldoConta;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Version;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Conta {
    @Id
    @GeneratedValue
    private Long id;

    @Version
    private Long version = 0L;

    @ManyToOne(fetch = FetchType.LAZY)
    private Usuario usuario;

    @Column(nullable = false, unique = true)
    private String numeroConta;

    @Column(name = "tipo_saldo_conta", nullable = false)
    private TipoSaldoConta tipoSaldoConta;

    @Column(nullable = false)
    private BigDecimal saldo = BigDecimal.ZERO;
}
