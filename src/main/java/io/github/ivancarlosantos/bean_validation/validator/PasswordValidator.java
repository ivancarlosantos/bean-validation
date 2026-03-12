package io.github.ivancarlosantos.bean_validation.validator;

import io.github.ivancarlosantos.bean_validation.exception.VerifyFieldsException;
import io.github.ivancarlosantos.bean_validation.pattern.RegexPatterns;
import io.github.ivancarlosantos.bean_validation.valid.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
public class PasswordValidator implements Valid {

    @Override
    public String execute(String value) {
        PasswordValidator.log.info("Validating Password: {}", value);
        if (!value.matches(RegexPatterns.PASSWORD) && (value.length() < 8 || value.length() > 12)) {
            throw new VerifyFieldsException("Invalid Password format");
        }

        return value;
    }
}
