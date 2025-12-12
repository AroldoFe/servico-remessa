package br.com.aroldofe.servico_remessa.api.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DebitRequest {
    @NotNull(message = "O valor não pode ser nulo.")
    @DecimalMin(value = "0", inclusive = false, message = "O valor deve ser maior ou igual a zero.")
    private BigDecimal valor;
}
