package io.github.ivancarlosantos.bean_validation.validator;

import io.github.ivancarlosantos.bean_validation.masks.MaskedFields;
import io.github.ivancarlosantos.bean_validation.pattern.RegexPatterns;
import io.github.ivancarlosantos.bean_validation.valid.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoginValidator implements Valid {

    private MaskedFields mf = new MaskedFields();

    @Override
    public String execute(String value) {
        LoginValidator.log.info("Validating Login: {}", mf.loginMask(value));
        if (!value.matches(RegexPatterns.LOGIN)) {
            throw new StringIndexOutOfBoundsException("Invalid Login format");
        }
        return value;
    }
}