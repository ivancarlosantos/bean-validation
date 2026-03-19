package io.github.ivancarlosantos.bean_validation.validator;

import io.github.ivancarlosantos.bean_validation.masks.MaskedFields;
import io.github.ivancarlosantos.bean_validation.pattern.RegexPatterns;
import io.github.ivancarlosantos.bean_validation.valid.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PasswordValidator implements Valid {

    private MaskedFields mf = new MaskedFields();

    @Override
    public String execute(String value) {
        PasswordValidator.log.info("Validating Password: {}", mf.passwordMask(value));
        if (!value.matches(RegexPatterns.PASSWORD) && (value.length() < 8 || value.length() > 12)) {
            throw new StringIndexOutOfBoundsException("Invalid Password format");
        }

        return value;
    }
}
