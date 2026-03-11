package io.github.ivancarlosantos.bean_validation.validator;

import io.github.ivancarlosantos.bean_validation.pattern.RegexPatterns;
import io.github.ivancarlosantos.bean_validation.valid.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EmailValidator implements Valid {

    @Override
    public void execute(String value) {
        EmailValidator.log.info("Validating email: {}", value);
        if (!value.matches(RegexPatterns.EMAIL)) {
            throw new IllegalArgumentException("Invalid email format");
        }
    }
}