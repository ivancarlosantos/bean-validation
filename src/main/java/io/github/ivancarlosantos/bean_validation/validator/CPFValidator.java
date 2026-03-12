package io.github.ivancarlosantos.bean_validation.validator;

import io.github.ivancarlosantos.bean_validation.exception.VerifyFieldsException;
import io.github.ivancarlosantos.bean_validation.pattern.RegexPatterns;
import io.github.ivancarlosantos.bean_validation.valid.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
public class CPFValidator implements Valid {

    @Override
    public String execute(String value) {
        CPFValidator.log.info("Validating CPF: {}", value);
        if (!value.matches(RegexPatterns.CPF) && value.length() != 11) {
            throw new VerifyFieldsException("Invalid CPF format");
        }
        return value;
    }
}
