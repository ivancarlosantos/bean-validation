package io.github.ivancarlosantos.bean_validation.validator;

import io.github.ivancarlosantos.bean_validation.exception.VerifyFieldsException;
import io.github.ivancarlosantos.bean_validation.pattern.RegexPatterns;
import io.github.ivancarlosantos.bean_validation.valid.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
public class PhoneValidator implements Valid {

    @Override
    public String execute(String value) {
        PhoneValidator.log.info("Validating Phone number: {}", value);
        if (!value.matches(RegexPatterns.PHONE) && value.length() != 10) {
            throw new VerifyFieldsException("Invalid Phone format");
        }

        return value;
    }
}
