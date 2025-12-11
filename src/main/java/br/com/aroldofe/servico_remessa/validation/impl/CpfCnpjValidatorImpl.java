package br.com.aroldofe.servico_remessa.validation.impl;

import br.com.aroldofe.servico_remessa.utils.Constants;
import br.com.aroldofe.servico_remessa.validation.CpfCnpj;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Objects;

public class CpfCnpjValidatorImpl implements ConstraintValidator<CpfCnpj, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (Objects.isNull(value)) {
            return true;
        }


        return isValidCpf(value) || isValidCnpj(value);
    }

    private boolean isValidCpf(String cpf) {
        return cpf.matches(Constants.CPF_REGEX);
    }

    private boolean isValidCnpj(String cnpj) {
        return cnpj.matches(Constants.CNPJ_REGEX);
    }
}
