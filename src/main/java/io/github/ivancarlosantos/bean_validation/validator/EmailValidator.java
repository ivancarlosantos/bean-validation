package io.github.ivancarlosantos.bean_validation.validator;

import io.github.ivancarlosantos.bean_validation.exception.VerifyFieldsException;
import io.github.ivancarlosantos.bean_validation.masks.MaskedFields;
import io.github.ivancarlosantos.bean_validation.pattern.RegexPatterns;
import io.github.ivancarlosantos.bean_validation.valid.Valid;
import java.util.logging.Logger;


public class EmailValidator implements Valid {

    private final MaskedFields mf = new MaskedFields();
    private static final Logger LOGGER = Logger.getLogger(EmailValidator.class.getName());

    @Override
    public String execute(String value) {
        if (value == null || !value.matches(RegexPatterns.EMAIL)) {
            throw new VerifyFieldsException("Invalid email format");
        }

        LOGGER.info("Validating email: " + mf.emailMask(value));
        return value;
    }
}