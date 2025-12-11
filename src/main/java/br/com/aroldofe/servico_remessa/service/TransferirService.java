package br.com.aroldofe.servico_remessa.service;

import java.math.BigDecimal;

public interface TransferirService {
    void transfer(Long contaOrigemId, Long contaDestinoId, BigDecimal valor);
}
