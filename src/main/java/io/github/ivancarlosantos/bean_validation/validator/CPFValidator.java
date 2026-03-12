package io.github.ivancarlosantos.bean_validation.validator;

import io.github.ivancarlosantos.bean_validation.exception.VerifyFieldsException;
import io.github.ivancarlosantos.bean_validation.pattern.RegexPatterns;
import io.github.ivancarlosantos.bean_validation.valid.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CPFValidator implements Valid {

    private String cpf;

    public CPFValidator(String cpf) {
        this.cpf = cpf;
    }

    @Override
    public void execute(String value) {
        this.cpf = value;
        CPFValidator.log.info("Validating CPF: {}", cpf);
        if (!cpf.matches(RegexPatterns.CPF) && cpf.length() != 11) {
            throw new VerifyFieldsException("Invalid CPF format");
        }
    }
}
