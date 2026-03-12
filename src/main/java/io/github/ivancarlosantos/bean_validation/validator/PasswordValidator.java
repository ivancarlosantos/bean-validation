package io.github.ivancarlosantos.bean_validation.validator;

import io.github.ivancarlosantos.bean_validation.exception.VerifyFieldsException;
import io.github.ivancarlosantos.bean_validation.pattern.RegexPatterns;
import io.github.ivancarlosantos.bean_validation.valid.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PasswordValidator implements Valid {

    private String password;

    public PasswordValidator(String password) {
        this.password = password;
    }

    @Override
    public void execute(String value) {
        this.password = value;
        PasswordValidator.log.info("Validating Password: {}", password);
        if (!password.matches(RegexPatterns.PASSWORD) && (password.length() < 8 || password.length() > 12)) {
            throw new VerifyFieldsException("Invalid Password format");
        }
    }
}
