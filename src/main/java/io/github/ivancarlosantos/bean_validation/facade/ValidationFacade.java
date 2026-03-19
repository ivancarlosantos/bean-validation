package io.github.ivancarlosantos.bean_validation.facade;

import io.github.ivancarlosantos.bean_validation.validator.*;

public class ValidationFacade {

    public static String cep(String value) {
        return new CEPValidator().execute(value);
    }

    public static String cpf(String value) {
        return new CPFValidator().execute(value);
    }

    public static String cnpj(String value) {
        return new CNPJValidator().execute(value);
    }

    public static String email(String value) {
        return new EmailValidator().execute(value);
    }

    public static String login(String value) {
        return new LoginValidator().execute(value);
    }

    public static String password(String value) {
        return new PasswordValidator().execute(value);
    }

    public static String phone(String value) {
        return new PhoneValidator().execute(value);
    }
}

