package io.github.ivancarlosantos.bean_validation.validator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("EmailValidator - Unit Tests")
class EmailValidatorTest {

    // ─── Valid inputs (no exception expected) ────────────────────────────────

    @ParameterizedTest(name = "Valid email: \"{0}\"")
    @ValueSource(strings = {
        "test@example.com",
        "user.name@domain.org",
        "user+tag@example.co.uk",
        "firstname.lastname@company.io",
        "user123@mail.net"
    })
    @DisplayName("Should NOT throw for valid email addresses")
    void shouldNotThrowForValidEmail(String email) {
        EmailValidator validator = new EmailValidator(email);
        assertDoesNotThrow(() -> validator.execute(email));
    }

    // ─── Invalid inputs (exception expected) ─────────────────────────────────

    @ParameterizedTest(name = "Invalid email: \"{0}\"")
    @ValueSource(strings = {
        "notanemail",
        "@nodomain.com",
        "spaces @domain.com",
        "missingdomain@",
        "nodot@nodot",
        "plainaddress"
    })
    @DisplayName("Should throw IllegalArgumentException for invalid email addresses")
    void shouldThrowForInvalidEmail(String email) {
        EmailValidator validator = new EmailValidator(email);
        assertThrows(IllegalArgumentException.class, () -> validator.execute(email));
    }

    // ─── Message validation ───────────────────────────────────────────────────

    @Test
    @DisplayName("Should throw with the correct exception message")
    void shouldThrowWithCorrectMessage() {
        String invalidEmail = "notanemail";
        EmailValidator validator = new EmailValidator(invalidEmail);

        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> validator.execute(invalidEmail)
        );
        assertEquals("Invalid email format", ex.getMessage());
    }

    @Test
    @DisplayName("Exception should be instance of IllegalArgumentException")
    void exceptionShouldBeIllegalArgumentException() {
        EmailValidator validator = new EmailValidator("bad");
        assertThrows(IllegalArgumentException.class, () -> validator.execute("bad"));
    }

    // ─── execute() updates internal state ────────────────────────────────────

    @Test
    @DisplayName("execute() should use the value passed as argument, not constructor value")
    void executeShouldUseArgumentValue() {
        // Constructed with invalid email but executed with a valid one
        EmailValidator validator = new EmailValidator("bad");
        assertDoesNotThrow(() -> validator.execute("test@example.com"));
    }

    @Test
    @DisplayName("Should throw when execute() is called with invalid value, even if constructor had valid value")
    void shouldThrowWhenExecutedWithInvalidEmail() {
        EmailValidator validator = new EmailValidator("valid@example.com");
        assertThrows(IllegalArgumentException.class, () -> validator.execute("invalid-email"));
    }
}

