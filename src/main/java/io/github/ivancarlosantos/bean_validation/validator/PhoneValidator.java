package io.github.ivancarlosantos.bean_validation.validator;

import io.github.ivancarlosantos.bean_validation.masks.MaskedFields;
import io.github.ivancarlosantos.bean_validation.pattern.RegexPatterns;
import io.github.ivancarlosantos.bean_validation.valid.Valid;


public class PhoneValidator implements Valid {

    private MaskedFields mf = new MaskedFields();

    @Override
    public String execute(String value) {
        System.out.println("Validating Phone number: " + mf.phoneMask(value));
        if (!value.matches(RegexPatterns.PHONE)) {
            throw new StringIndexOutOfBoundsException("Invalid Phone format");
        }

        return value;
    }
}
