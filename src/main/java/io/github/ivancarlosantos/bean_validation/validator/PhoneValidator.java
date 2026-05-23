package io.github.ivancarlosantos.bean_validation.validator;

import io.github.ivancarlosantos.bean_validation.exception.VerifyFieldsException;
import io.github.ivancarlosantos.bean_validation.masks.MaskedFields;
import io.github.ivancarlosantos.bean_validation.pattern.RegexPatterns;
import io.github.ivancarlosantos.bean_validation.valid.Valid;
import java.util.logging.Logger;


public class PhoneValidator implements Valid {

    private final MaskedFields mf = new MaskedFields();
    private static final Logger LOGGER = Logger.getLogger(PhoneValidator.class.getName());

    @Override
    public String execute(String value) {
        if (value == null || !value.matches(RegexPatterns.PHONE)) {
            throw new VerifyFieldsException("Invalid Phone format");
        }

        LOGGER.info("Validating Phone number: " + mf.phoneMask(value));

        return value;
    }
}
