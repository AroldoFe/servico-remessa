package br.com.aroldofe.servico_remessa.service;

import java.math.BigDecimal;

public interface DebitarService {
    void debit(Long contaId, BigDecimal valor);
}
