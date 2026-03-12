package io.github.ivancarlosantos.bean_validation.facade;

import io.github.ivancarlosantos.bean_validation.validator.*;

public class ValidationFacade {

    public static CEPValidator cep(String cep) {
        return new CEPValidator(cep);
    }

    public static CPFValidator cpf(String cpf) {
        return new CPFValidator(cpf);
    }

    public static EmailValidator email(String email) {
        return new EmailValidator(email);
    }

    public static LoginValidator login(String login) {
        return new LoginValidator(login);
    }

    public static PasswordValidator password(String password) {
        return new PasswordValidator(password);
    }

    public static PhoneValidator phone(String phone) {
        return new PhoneValidator(phone);
    }
}
