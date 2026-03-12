package io.github.ivancarlosantos.bean_validation.validator;

import io.github.ivancarlosantos.bean_validation.exception.VerifyFieldsException;
import io.github.ivancarlosantos.bean_validation.pattern.RegexPatterns;
import io.github.ivancarlosantos.bean_validation.valid.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PhoneValidator implements Valid {

    private String phone;

    public PhoneValidator(String phone) {
        this.phone = phone;
    }

    @Override
    public void execute(String value) {
        this.phone = value;
        PhoneValidator.log.info("Validating Phone number: {}", phone);
        if (!phone.matches(RegexPatterns.PHONE) && phone.length() != 10) {
            throw new VerifyFieldsException("Invalid Phone format");
        }
    }
}
