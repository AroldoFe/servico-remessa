package br.com.aroldofe.servico_remessa.service.impl;

import br.com.aroldofe.servico_remessa.api.bo.ContaBO;
import br.com.aroldofe.servico_remessa.api.mapper.ContaMapper;
import br.com.aroldofe.servico_remessa.exception.ContaAlreadyExistsException;
import br.com.aroldofe.servico_remessa.exception.NotFoundException;
import br.com.aroldofe.servico_remessa.repository.ContaRepository;
import br.com.aroldofe.servico_remessa.repository.UsuarioRepository;
import br.com.aroldofe.servico_remessa.service.ContaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

import static br.com.aroldofe.servico_remessa.repository.specification.ContaSpecification.hasNumeroConta;
import static br.com.aroldofe.servico_remessa.repository.specification.ContaSpecification.hasTipoSaldoConta;
import static br.com.aroldofe.servico_remessa.repository.specification.ContaSpecification.hasUsuarioId;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class ContaServiceImpl implements ContaService {
    private final ContaRepository repository;
    private final UsuarioRepository usuarioRepository;

    @Override
    @Transactional
    public ContaBO create(ContaBO contaBO) {
        final var usuario = this.usuarioRepository.findById(contaBO.getUsuarioId())
                .orElseThrow(() -> new NotFoundException("Usuário não encontrado."));

        final var existsContaWithTipoSaldoConta = this.repository.exists(
                hasTipoSaldoConta(contaBO.getTipoSaldoConta())
                        .and(hasUsuarioId(usuario.getId()))
        );

        if (existsContaWithTipoSaldoConta) {
            throw new ContaAlreadyExistsException("Já existe uma conta para este tipo de saldo e usuário.");
        }

        final var existsContaWithNumeroConta = this.repository.exists(hasNumeroConta(contaBO.getNumeroConta()));

        if (existsContaWithNumeroConta) {
            throw new ContaAlreadyExistsException("Já existe uma conta com este número");
        }

        var conta = ContaMapper.toEntity(contaBO)
                .toBuilder()
                .usuario(usuario)
                .saldo(BigDecimal.ZERO)
                .build();
        conta = this.repository.save(conta);
        return ContaMapper.toBO(conta);
    }

    @Override
    public Optional<ContaBO> findById(Long id) {
        return this.repository.findById(id).map(ContaMapper::toBO);
    }

    @Override
    public Collection<ContaBO> findByUsuarioId(Long usuarioId) {
        return this.repository.findAllByUsuarioId(usuarioId)
                .stream()
                .map(ContaMapper::toBO)
                .collect(Collectors.toList());
    }
}
