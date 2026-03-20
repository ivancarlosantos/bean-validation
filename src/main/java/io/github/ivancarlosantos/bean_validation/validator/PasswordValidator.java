package io.github.ivancarlosantos.bean_validation.validator;

import io.github.ivancarlosantos.bean_validation.masks.MaskedFields;
import io.github.ivancarlosantos.bean_validation.pattern.RegexPatterns;
import io.github.ivancarlosantos.bean_validation.valid.Valid;


public class PasswordValidator implements Valid {

    private MaskedFields mf = new MaskedFields();

    @Override
    public String execute(String value) {
        System.out.println("Validating Password: " + mf.passwordMask(value));
        if (!value.matches(RegexPatterns.PASSWORD) && (value.length() < 8 || value.length() > 12)) {
            throw new StringIndexOutOfBoundsException("Invalid Password format");
        }

        return value;
    }
}
