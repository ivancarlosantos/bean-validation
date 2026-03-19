package io.github.ivancarlosantos.bean_validation.validator;

import io.github.ivancarlosantos.bean_validation.masks.MaskedFields;
import io.github.ivancarlosantos.bean_validation.pattern.RegexPatterns;
import io.github.ivancarlosantos.bean_validation.valid.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CPFValidator implements Valid {

    private MaskedFields mf = new MaskedFields();

    @Override
    public String execute(String value) {
        CPFValidator.log.info("Validating CPF: {}", mf.cpfMask(value));
        if (!value.matches(RegexPatterns.CPF) && value.length() != 14) {
            throw new StringIndexOutOfBoundsException("Invalid CPF format");
        }
        return value;
    }
}
