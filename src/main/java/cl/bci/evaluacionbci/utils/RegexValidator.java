package cl.bci.evaluacionbci.utils;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

public class RegexValidator implements ConstraintValidator<ValidPassword, String> {

    @Value("${app.password.regex}")
    private String regex;

    @Autowired
    private MessageSource messageSource;
/*
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        if (!value.matches(regex)) {
            String messageTemplate = messageSource.getMessage("valid.password.message", null, LocaleContextHolder.getLocale());
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(messageTemplate).addConstraintViolation();
            return false;
        }
        return true;
    }
   */
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }
        return value.matches(regex);
    }


}