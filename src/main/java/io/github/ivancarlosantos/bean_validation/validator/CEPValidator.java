package io.github.ivancarlosantos.bean_validation.validator;

import io.github.ivancarlosantos.bean_validation.exception.VerifyFieldsException;
import io.github.ivancarlosantos.bean_validation.pattern.RegexPatterns;
import io.github.ivancarlosantos.bean_validation.valid.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CEPValidator implements Valid {

    @Override
    public String execute(String value) throws VerifyFieldsException {
        CEPValidator.log.info("Validating CEP: {}", value);
        if (!value.matches(RegexPatterns.CEP) && value.length() != 9) {
            throw new VerifyFieldsException("Invalid CEP format");
        }

        return value;
    }
}
