package br.com.aroldofe.servico_remessa.validation;

import br.com.aroldofe.servico_remessa.validation.impl.CpfCnpjValidatorImpl;
import jakarta.validation.Constraint;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = CpfCnpjValidatorImpl.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CpfCnpj {
    String message() default "CPF/CNPJ inválido";

    Class<?>[] groups() default {};

    Class<?>[] payload() default {};
}

