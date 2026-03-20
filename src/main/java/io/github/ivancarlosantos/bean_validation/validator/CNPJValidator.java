package io.github.ivancarlosantos.bean_validation.validator;


import io.github.ivancarlosantos.bean_validation.exception.VerifyFieldsException;
import io.github.ivancarlosantos.bean_validation.masks.MaskedFields;
import io.github.ivancarlosantos.bean_validation.pattern.RegexPatterns;
import io.github.ivancarlosantos.bean_validation.valid.Valid;


public class CNPJValidator implements Valid {

    private final MaskedFields mf = new MaskedFields();

    @Override
    public String execute(String value) {
        if (value == null || !value.matches(RegexPatterns.CNPJ)) {
            throw new VerifyFieldsException("Invalid CNPJ format");
        }

        System.out.println("Validating CNPJ: " + mf.cnpjMask(value));

        return value;
    }
}
