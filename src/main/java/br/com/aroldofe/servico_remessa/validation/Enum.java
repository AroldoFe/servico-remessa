package br.com.aroldofe.servico_remessa.validation;

import br.com.aroldofe.servico_remessa.validation.impl.EnumValidatorImpl;
import jakarta.validation.Constraint;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = EnumValidatorImpl.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Enum {
    String message() default "Valor inválido para o campo enum";

    Class<?> enumClass();

    Class<?>[] groups() default {};

    Class<?>[] payload() default {};
}
