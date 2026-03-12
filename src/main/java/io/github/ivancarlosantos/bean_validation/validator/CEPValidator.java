package io.github.ivancarlosantos.bean_validation.validator;

import io.github.ivancarlosantos.bean_validation.exception.VerifyFieldsException;
import io.github.ivancarlosantos.bean_validation.pattern.RegexPatterns;
import io.github.ivancarlosantos.bean_validation.valid.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CEPValidator implements Valid {

    private String cep;

    public CEPValidator(String cep) {
        this.cep = cep;
    }

    @Override
    public void execute(String value) {
        this.cep = value;
        CEPValidator.log.info("Validating CEP: {}", cep);
        if (!cep.matches(RegexPatterns.CEP) && cep.length() != 9) {
            throw new VerifyFieldsException("Invalid CEP format");
        }
    }
}
