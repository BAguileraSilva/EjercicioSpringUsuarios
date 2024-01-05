package cl.bci.evaluacionbci.utils;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = RegexValidator.class)
public @interface ValidPassword {
    String message() default "La contraseña no cumple con el formato requerido";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
