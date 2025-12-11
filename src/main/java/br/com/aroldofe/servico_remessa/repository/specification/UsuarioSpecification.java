package br.com.aroldofe.servico_remessa.repository.specification;

import br.com.aroldofe.servico_remessa.domain.Usuario;
import org.springframework.data.jpa.domain.Specification;

public class UsuarioSpecification {
    public static Specification<Usuario> findByEmail(String email) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("email"), email);
    }

    public static Specification<Usuario> findByCpfCnpj(String cpfCnpj) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("cpfCnpj"), cpfCnpj);
    }
}
