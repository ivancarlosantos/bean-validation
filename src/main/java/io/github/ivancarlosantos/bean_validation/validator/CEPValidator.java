package io.github.ivancarlosantos.bean_validation.validator;

import io.github.ivancarlosantos.bean_validation.exception.VerifyFieldsException;
import io.github.ivancarlosantos.bean_validation.masks.MaskedFields;
import io.github.ivancarlosantos.bean_validation.pattern.RegexPatterns;
import io.github.ivancarlosantos.bean_validation.valid.Valid;
import java.util.logging.Logger;


public class CEPValidator implements Valid {

    private final MaskedFields mf = new MaskedFields();
    private static final Logger LOGGER = Logger.getLogger(CEPValidator.class.getName());

    @Override
    public String execute(String value) {
        if (value == null || !value.matches(RegexPatterns.CEP)) {
            throw new VerifyFieldsException("Invalid CEP format");
        }

        LOGGER.info("Validating CEP: " + mf.cepMask(value));

        return value;
    }
}
