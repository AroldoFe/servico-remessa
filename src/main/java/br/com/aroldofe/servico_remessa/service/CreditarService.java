package br.com.aroldofe.servico_remessa.service;

import java.math.BigDecimal;

public interface CreditarService {
    void credit(Long contaId, BigDecimal valor);
}
