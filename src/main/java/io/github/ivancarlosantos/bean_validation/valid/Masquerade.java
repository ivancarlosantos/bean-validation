package io.github.ivancarlosantos.bean_validation.valid;

public interface Masquerade {

    String cepMask(String cep);

    String cpfMask(String cpf);

    String cnpjMask(String cnpj);

    String emailMask(String email);

    String loginMask(String login);

    String passwordMask(String password);

    String phoneMask(String phone);
}
