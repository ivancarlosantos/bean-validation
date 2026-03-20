package io.github.ivancarlosantos.bean_validation.validator;

import io.github.ivancarlosantos.bean_validation.masks.MaskedFields;
import io.github.ivancarlosantos.bean_validation.pattern.RegexPatterns;
import io.github.ivancarlosantos.bean_validation.valid.Valid;


public class CEPValidator implements Valid {

    private MaskedFields mf = new MaskedFields();

    @Override
    public String execute(String value) throws StringIndexOutOfBoundsException {
        System.out.println("Validating CEP: " + mf.cepMask(value));
        if (!value.matches(RegexPatterns.CEP) && value.length() != 9) {
            throw new StringIndexOutOfBoundsException("Invalid CEP format");
        }

        return value;
    }
}
