package io.github.ivancarlosantos.bean_validation.validator;

import io.github.ivancarlosantos.bean_validation.pattern.RegexPatterns;
import io.github.ivancarlosantos.bean_validation.valid.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EmailValidator implements Valid {

    private String email;

    public EmailValidator(String email) {
        this.email = email;
    }

    @Override
    public void execute(String value) {
        this.email = value;
        EmailValidator.log.info("Validating email: {}", email);
        if (!email.matches(RegexPatterns.EMAIL)) {
            throw new IllegalArgumentException("Invalid email format");
        }
    }
}