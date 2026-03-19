package io.github.ivancarlosantos.bean_validation.pattern;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.lang.reflect.Constructor;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("RegexPatterns - Unit Tests")
class RegexPatternsTest {

    // ─────────────────────────────────────────────────────────────────────────
    // Utility class
    // ─────────────────────────────────────────────────────────────────────────

    @Test
    @DisplayName("Should have a private constructor (utility class)")
    void shouldHavePrivateConstructor() throws Exception {
        Constructor<RegexPatterns> constructor = RegexPatterns.class.getDeclaredConstructor();
        assertFalse(constructor.canAccess(null));
        constructor.setAccessible(true);
        assertNotNull(constructor.newInstance());
    }

    // ─────────────────────────────────────────────────────────────────────────
    // LOGIN
    // ─────────────────────────────────────────────────────────────────────────

    @ParameterizedTest(name = "Valid login: \"{0}\"")
    @ValueSource(strings = {"user123", "User_Name", "user.name", "user-name", "abc", "login20"})
    @DisplayName("Should match valid LOGIN patterns")
    void shouldMatchValidLoginPatterns(String login) {
        assertTrue(login.matches(RegexPatterns.LOGIN));
    }

    @ParameterizedTest(name = "Invalid login: \"{0}\"")
    @ValueSource(strings = {"ab", "us er", "user!", "thisLoginNameIsTooLongToBeValid1234"})
    @DisplayName("Should NOT match invalid LOGIN patterns")
    void shouldNotMatchInvalidLoginPatterns(String login) {
        assertFalse(login.matches(RegexPatterns.LOGIN));
    }

    // ─────────────────────────────────────────────────────────────────────────
    // EMAIL
    // ─────────────────────────────────────────────────────────────────────────

    @ParameterizedTest(name = "Valid email: \"{0}\"")
    @ValueSource(strings = {
        "test@example.com",
        "user.name@domain.org",
        "user+tag@example.co.uk",
        "firstname.lastname@company.io"
    })
    @DisplayName("Should match valid EMAIL patterns")
    void shouldMatchValidEmailPatterns(String email) {
        assertTrue(email.matches(RegexPatterns.EMAIL));
    }

    @ParameterizedTest(name = "Invalid email: \"{0}\"")
    @ValueSource(strings = {
        "notanemail",
        "@nodomain.com",
        "spaces @domain.com",
        "missingdomain@",
        "nodot@nodot"
    })
    @DisplayName("Should NOT match invalid EMAIL patterns")
    void shouldNotMatchInvalidEmailPatterns(String email) {
        assertFalse(email.matches(RegexPatterns.EMAIL));
    }

    // ─────────────────────────────────────────────────────────────────────────
    // CPF
    // ─────────────────────────────────────────────────────────────────────────

    @ParameterizedTest(name = "Valid CPF: \"{0}\"")
    @ValueSource(strings = {"123.456.789-09", "987.654.321-00", "000.000.000-00"})
    @DisplayName("Should match valid CPF patterns")
    void shouldMatchValidCPFPatterns(String cpf) {
        assertTrue(cpf.matches(RegexPatterns.CPF));
    }

    @ParameterizedTest(name = "Invalid CPF: \"{0}\"")
    @ValueSource(strings = {"12345678909", "123.456.789", "123456789-09", "ABC.DEF.GHI-JK"})
    @DisplayName("Should NOT match invalid CPF patterns")
    void shouldNotMatchInvalidCPFPatterns(String cpf) {
        assertFalse(cpf.matches(RegexPatterns.CPF));
    }

    // ─────────────────────────────────────────────────────────────────────────
    // CNPJ
    // ─────────────────────────────────────────────────────────────────────────

    @ParameterizedTest(name = "Valid CNPJ: \"{0}\"")
    @ValueSource(strings = {"59.456.277/0001-76", "01.234.567/0001-89", "00.000.000/0000-00"})
    @DisplayName("Should match valid CNPJ patterns")
    void shouldMatchValidCNPJPatterns(String cnpj) {
        assertTrue(cnpj.matches(RegexPatterns.CNPJ));
    }

    @ParameterizedTest(name = "Invalid CNPJ: \"{0}\"")
    @ValueSource(strings = {"12345678909", "00.000.ABC/0000-00", "00.000.000\\0000-00", "ABC.DEF.GHI-JK"})
    @DisplayName("Should NOT match invalid CNPJ patterns")
    void shouldNotMatchInvalidCNPJPatterns(String cnpj) {
        assertFalse(cnpj.matches(RegexPatterns.CNPJ));
    }

    // ─────────────────────────────────────────────────────────────────────────
    // PHONE
    // ─────────────────────────────────────────────────────────────────────────

    @ParameterizedTest(name = "Valid phone: \"{0}\"")
    @ValueSource(strings = {
        "5511999999999",
        "+5511999999999",
        "+55 (11) 99999-9999",
        "55(11)99999-9999"
    })
    @DisplayName("Should match valid PHONE patterns")
    void shouldMatchValidPhonePatterns(String phone) {
        assertTrue(phone.matches(RegexPatterns.PHONE));
    }

    @ParameterizedTest(name = "Invalid phone: \"{0}\"")
    @ValueSource(strings = {"99999999", "1234", "phone-number", "0000"})
    @DisplayName("Should NOT match invalid PHONE patterns")
    void shouldNotMatchInvalidPhonePatterns(String phone) {
        assertFalse(phone.matches(RegexPatterns.PHONE));
    }

    // ─────────────────────────────────────────────────────────────────────────
    // CEP
    // ─────────────────────────────────────────────────────────────────────────

    @ParameterizedTest(name = "Valid CEP: \"{0}\"")
    @ValueSource(strings = {"12345-678", "12345678"})
    @DisplayName("Should match valid CEP patterns")
    void shouldMatchValidCEPPatterns(String cep) {
        assertTrue(cep.matches(RegexPatterns.CEP));
    }

    @ParameterizedTest(name = "Invalid CEP: \"{0}\"")
    @ValueSource(strings = {"1234-567", "ABCDE-123", "1234", "12345-6789"})
    @DisplayName("Should NOT match invalid CEP patterns")
    void shouldNotMatchInvalidCEPPatterns(String cep) {
        assertFalse(cep.matches(RegexPatterns.CEP));
    }

    // ─────────────────────────────────────────────────────────────────────────
    // PASSWORD
    // ─────────────────────────────────────────────────────────────────────────

    @ParameterizedTest(name = "Valid password: \"{0}\"")
    @ValueSource(strings = {"Password1@", "Str0ng!Pw", "S3cur3P@ss", "Abc12345#"})
    @DisplayName("Should match valid PASSWORD patterns")
    void shouldMatchValidPasswordPatterns(String password) {
        assertTrue(password.matches(RegexPatterns.PASSWORD));
    }

    @ParameterizedTest(name = "Invalid password: \"{0}\"")
    @ValueSource(strings = {
        "password",       // no uppercase, digit, special
        "PASSWORD1@",     // no lowercase
        "Pass1@",         // too short
        "password123"     // no uppercase, no special char
    })
    @DisplayName("Should NOT match invalid PASSWORD patterns")
    void shouldNotMatchInvalidPasswordPatterns(String password) {
        assertFalse(password.matches(RegexPatterns.PASSWORD));
    }

    // ─────────────────────────────────────────────────────────────────────────
    // Constant non-null check
    // ─────────────────────────────────────────────────────────────────────────

    @Test
    @DisplayName("All regex constants should be non-null and non-empty")
    void allConstantsShouldBeNonNullAndNonEmpty() {
        assertAll(
            () -> assertNotNull(RegexPatterns.LOGIN),
            () -> assertNotNull(RegexPatterns.EMAIL),
            () -> assertNotNull(RegexPatterns.CPF),
            () -> assertNotNull(RegexPatterns.CNPJ),
            () -> assertNotNull(RegexPatterns.PHONE),
            () -> assertNotNull(RegexPatterns.CEP),
            () -> assertNotNull(RegexPatterns.PASSWORD),
            () -> assertFalse(RegexPatterns.LOGIN.isBlank()),
            () -> assertFalse(RegexPatterns.EMAIL.isBlank()),
            () -> assertFalse(RegexPatterns.CPF.isBlank()),
            () -> assertFalse(RegexPatterns.CNPJ.isBlank()),
            () -> assertFalse(RegexPatterns.PHONE.isBlank()),
            () -> assertFalse(RegexPatterns.CEP.isBlank()),
            () -> assertFalse(RegexPatterns.PASSWORD.isBlank())
        );
    }
}