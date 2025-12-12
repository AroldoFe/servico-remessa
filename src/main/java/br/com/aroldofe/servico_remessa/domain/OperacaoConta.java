package br.com.aroldofe.servico_remessa.domain;

import br.com.aroldofe.servico_remessa.enums.TipoOperacao;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OperacaoConta {
    @Id
    @GeneratedValue
    private Long id;

    @Version
    private Long version = 0L;

    @ManyToOne(fetch = FetchType.LAZY)
    private Conta conta;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoOperacao tipoOperacao;

    @Column(nullable = false)
    private BigDecimal valor;

    @Column(nullable = false)
    @CreationTimestamp
    private LocalDateTime dataHoraOperacao;
}
