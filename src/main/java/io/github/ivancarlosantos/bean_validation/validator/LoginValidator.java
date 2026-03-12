package io.github.ivancarlosantos.bean_validation.validator;

import io.github.ivancarlosantos.bean_validation.exception.VerifyFieldsException;
import io.github.ivancarlosantos.bean_validation.pattern.RegexPatterns;
import io.github.ivancarlosantos.bean_validation.valid.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoginValidator implements Valid {

    private String login;

    public LoginValidator(String login) {
        this.login = login;
    }

    @Override
    public void execute(String value) {
        this.login = value;
        LoginValidator.log.info("Validating Login: {}", login);
        if (!login.matches(RegexPatterns.LOGIN)) {
            throw new VerifyFieldsException("Invalid Login format");
        }
    }
}