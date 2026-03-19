package io.github.ivancarlosantos.bean_validation.masks;

import io.github.ivancarlosantos.bean_validation.valid.Masquerade;

public class MaskedFields implements Masquerade {

    @Override
    public String cepMask(String cep) {
        return cep.replace(cep, cep.substring(0, 2) + "***-" + cep.substring(cep.length() - 3));
    }

    @Override
    public String cpfMask(String cpf) {
        return cpf.replace(cpf, cpf.substring(0, 3) + ".***.***-" + cpf.substring(12));
    }

    @Override
    public String cnpjMask(String cnpj) {
        return cnpj.replace(cnpj, cnpj.substring(0, 2) + ".***.***/****-" + cnpj.substring(16));
    }

    @Override
    public String emailMask(String email) {
        return email.replace(email, email.substring(0, 1) + "****" + email.substring(email.indexOf("@") - 1));
    }

    @Override
    public String loginMask(String login) {
        return login.replace(login, login.substring(0, 1) + "***.***" + login.substring(login.length() - 1));
    }

    @Override
    public String passwordMask(String password) {
        return password.replace(password, "****");
    }

    @Override
    public String phoneMask(String phone) {
        return phone.replace(phone, phone.substring(0, 5) + "*****" + phone.substring(phone.length() - 3));
    }
}
