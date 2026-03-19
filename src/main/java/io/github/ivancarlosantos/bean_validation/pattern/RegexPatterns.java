package io.github.ivancarlosantos.bean_validation.pattern;

public final class RegexPatterns {

    private RegexPatterns() {
    }

    public static final String LOGIN =
            "^[a-zA-Z0-9._-]{3,20}$";

    public static final String EMAIL =
            "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

    public static final String CPF =
            "^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$";

    public static final String PHONE =
            "^\\+?55\\s?\\(?\\d{2}\\)?\\s?\\d{5}-?\\d{4}$";

    public static final String CEP =
            "^\\d{5}-?\\d{3}$";

    public static final String PASSWORD =
            "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,}$";

    public static final String CNPJ =
            "^\\d{2}\\.\\d{3}\\.\\d{3}/\\d{4}-\\d{2}$";
}