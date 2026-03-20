package io.github.ivancarlosantos.bean_validation.validator;

import io.github.ivancarlosantos.bean_validation.exception.VerifyFieldsException;
import io.github.ivancarlosantos.bean_validation.masks.MaskedFields;
import io.github.ivancarlosantos.bean_validation.pattern.RegexPatterns;
import io.github.ivancarlosantos.bean_validation.valid.Valid;


public class CEPValidator implements Valid {

    private final MaskedFields mf = new MaskedFields();

    @Override
    public String execute(String value) {
        if (value == null || !value.matches(RegexPatterns.CEP)) {
            throw new VerifyFieldsException("Invalid CEP format");
        }

        System.out.println("Validating CEP: " + mf.cepMask(value));

        return value;
    }
}
