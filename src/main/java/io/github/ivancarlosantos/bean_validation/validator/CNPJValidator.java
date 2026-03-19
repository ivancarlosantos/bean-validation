package io.github.ivancarlosantos.bean_validation.validator;


import io.github.ivancarlosantos.bean_validation.masks.MaskedFields;
import io.github.ivancarlosantos.bean_validation.pattern.RegexPatterns;
import io.github.ivancarlosantos.bean_validation.valid.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CNPJValidator implements Valid {

    private MaskedFields mf = new MaskedFields();

    @Override
    public String execute(String value) throws StringIndexOutOfBoundsException {
        CNPJValidator.log.info("Validating CNPJ: {}", mf.cnpjMask(value));
        if (!value.matches(RegexPatterns.CNPJ) && value.length() != 18) {
            throw new StringIndexOutOfBoundsException("Invalid CNPJ format");
        }

        return value;
    }
}
