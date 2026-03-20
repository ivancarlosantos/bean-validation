package io.github.ivancarlosantos.bean_validation.validator;

import io.github.ivancarlosantos.bean_validation.masks.MaskedFields;
import io.github.ivancarlosantos.bean_validation.pattern.RegexPatterns;
import io.github.ivancarlosantos.bean_validation.valid.Valid;


public class EmailValidator implements Valid {

    private MaskedFields mf = new MaskedFields();

    @Override
    public String execute(String value) {
        System.out.println("Validating email: " + mf.emailMask(value));
        if (!value.matches(RegexPatterns.EMAIL)) {
            throw new StringIndexOutOfBoundsException("Invalid email format");
        }
        return value;
    }
}