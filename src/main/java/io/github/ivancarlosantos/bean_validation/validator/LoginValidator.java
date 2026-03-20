package io.github.ivancarlosantos.bean_validation.validator;

import io.github.ivancarlosantos.bean_validation.exception.VerifyFieldsException;
import io.github.ivancarlosantos.bean_validation.masks.MaskedFields;
import io.github.ivancarlosantos.bean_validation.pattern.RegexPatterns;
import io.github.ivancarlosantos.bean_validation.valid.Valid;


public class LoginValidator implements Valid {

    private final MaskedFields mf = new MaskedFields();

    @Override
    public String execute(String value) {
        if (value == null || !value.matches(RegexPatterns.LOGIN)) {
            throw new VerifyFieldsException("Invalid Login format");
        }

        System.out.println("Validating Login: " + mf.loginMask(value));
        return value;
    }
}