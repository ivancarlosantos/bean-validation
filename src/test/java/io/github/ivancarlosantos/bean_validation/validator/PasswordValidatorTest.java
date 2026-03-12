package io.github.ivancarlosantos.bean_validation.validator;

import io.github.ivancarlosantos.bean_validation.exception.VerifyFieldsException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("PasswordValidator - Unit Tests")
class PasswordValidatorTest {

    // ─── Valid inputs (no exception expected) ────────────────────────────────
    // Valid password: at least 1 uppercase, 1 lowercase, 1 digit, 1 special char (@#$%^&+=!), min 8 chars

    @ParameterizedTest(name = "Valid password: \"{0}\"")
    @ValueSource(strings = {
        "Password1@",
        "Str0ng!Pw",
        "S3cur3P@ss",
        "Abc12345#",
        "Test@1234"
    })
    @DisplayName("Should NOT throw for valid passwords matching the regex")
    void shouldNotThrowForValidPassword(String password) {
        PasswordValidator validator = new PasswordValidator(password);
        assertDoesNotThrow(() -> validator.execute(password));
    }

    // ─── Invalid inputs (exception expected) ─────────────────────────────────
    // Exception thrown only when: !matches regex AND (length < 8 OR length > 12)

    @ParameterizedTest(name = "Invalid password (too short): \"{0}\"")
    @ValueSource(strings = {
        "weak",      // length 4 < 8, no regex match
        "bad",       // length 3 < 8, no regex match
        "Ab1@"       // length 4 < 8, no regex match
    })
    @DisplayName("Should throw VerifyFieldsException for password shorter than 8 chars that does not match regex")
    void shouldThrowForTooShortPassword(String password) {
        PasswordValidator validator = new PasswordValidator(password);
        assertThrows(VerifyFieldsException.class, () -> validator.execute(password));
    }

    @ParameterizedTest(name = "Invalid password (too long, no regex): \"{0}\"")
    @ValueSource(strings = {
        "weakpassword1234!",       // length 17 > 12, no uppercase → no match
        "nouppercase12345!"        // length 17 > 12, no uppercase → no match
    })
    @DisplayName("Should throw VerifyFieldsException for password longer than 12 chars that does not match regex")
    void shouldThrowForTooLongInvalidPassword(String password) {
        PasswordValidator validator = new PasswordValidator(password);
        assertThrows(VerifyFieldsException.class, () -> validator.execute(password));
    }

    // ─── Message validation ───────────────────────────────────────────────────

    @Test
    @DisplayName("Should throw with the correct exception message")
    void shouldThrowWithCorrectMessage() {
        String invalidPassword = "weak";
        PasswordValidator validator = new PasswordValidator(invalidPassword);

        VerifyFieldsException ex = assertThrows(
                VerifyFieldsException.class,
                () -> validator.execute(invalidPassword)
        );
        assertEquals("Invalid Password format", ex.getMessage());
    }

    @Test
    @DisplayName("Exception should be instance of RuntimeException")
    void exceptionShouldBeRuntimeException() {
        PasswordValidator validator = new PasswordValidator("weak");
        VerifyFieldsException ex = assertThrows(VerifyFieldsException.class,
                () -> validator.execute("weak"));
        assertInstanceOf(RuntimeException.class, ex);
    }

    // ─── execute() updates internal state ────────────────────────────────────

    @Test
    @DisplayName("execute() should use the value passed as argument, not constructor value")
    void executeShouldUseArgumentValue() {
        // Constructed with invalid password but executed with a valid one
        PasswordValidator validator = new PasswordValidator("weak");
        assertDoesNotThrow(() -> validator.execute("Password1@"));
    }

    @Test
    @DisplayName("Should throw when execute() is called with invalid value")
    void shouldThrowWhenExecutedWithInvalidPassword() {
        PasswordValidator validator = new PasswordValidator("Password1@");
        assertThrows(VerifyFieldsException.class, () -> validator.execute("weak"));
    }
}

