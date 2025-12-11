package br.com.aroldofe.servico_remessa.validation.impl;

import br.com.aroldofe.servico_remessa.validation.Enum;
import jakarta.validation.ConstraintValidator;

public class EnumValidatorImpl implements ConstraintValidator<Enum, String> {

    private Class<?> enumClass;

    @Override
    public void initialize(Enum constraintAnnotation) {
        this.enumClass = constraintAnnotation.enumClass();
    }

    @Override
    public boolean isValid(String value, jakarta.validation.ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        Object[] enumConstants = enumClass.getEnumConstants();
        for (Object enumConstant : enumConstants) {
            if (enumConstant.toString().equals(value)) {
                return true;
            }
        }
        return false;
    }
}
