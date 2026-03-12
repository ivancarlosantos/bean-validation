package io.github.ivancarlosantos.bean_validation.validator;

import io.github.ivancarlosantos.bean_validation.pattern.RegexPatterns;
import io.github.ivancarlosantos.bean_validation.valid.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
public class EmailValidator implements Valid {

    @Override
    public String execute(String value) {
        EmailValidator.log.info("Validating email: {}", value);
        if (!value.matches(RegexPatterns.EMAIL)) {
            throw new IllegalArgumentException("Invalid email format");
        }
        return value;
    }
}