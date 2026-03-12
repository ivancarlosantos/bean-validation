package io.github.ivancarlosantos.bean_validation.validator;

import io.github.ivancarlosantos.bean_validation.exception.VerifyFieldsException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("LoginValidator - Unit Tests")
class LoginValidatorTest {

    // ─── Valid inputs (no exception expected) ────────────────────────────────

    @ParameterizedTest(name = "Valid login: \"{0}\"")
    @ValueSource(strings = {
        "user123",
        "User_Name",
        "user.name",
        "user-name",
        "abc",
        "login2025",
        "a1b"
    })
    @DisplayName("Should NOT throw for valid login values")
    void shouldNotThrowForValidLogin(String login) {
        LoginValidator validator = new LoginValidator(login);
        assertDoesNotThrow(() -> validator.execute(login));
    }

    // ─── Invalid inputs (exception expected) ─────────────────────────────────

    @ParameterizedTest(name = "Invalid login: \"{0}\"")
    @ValueSource(strings = {
        "ab",                                    // too short (< 3 chars)
        "us er",                                 // contains space
        "user!",                                 // special char not allowed
        "thisLoginNameIsTooLongToBeValid1234"    // too long (> 20 chars)
    })
    @DisplayName("Should throw VerifyFieldsException for invalid login values")
    void shouldThrowForInvalidLogin(String login) {
        LoginValidator validator = new LoginValidator(login);
        assertThrows(VerifyFieldsException.class, () -> validator.execute(login));
    }

    // ─── Message validation ───────────────────────────────────────────────────

    @Test
    @DisplayName("Should throw with the correct exception message")
    void shouldThrowWithCorrectMessage() {
        String invalidLogin = "ab";
        LoginValidator validator = new LoginValidator(invalidLogin);

        VerifyFieldsException ex = assertThrows(
                VerifyFieldsException.class,
                () -> validator.execute(invalidLogin)
        );
        assertEquals("Invalid Login format", ex.getMessage());
    }

    @Test
    @DisplayName("Exception should be instance of RuntimeException")
    void exceptionShouldBeRuntimeException() {
        LoginValidator validator = new LoginValidator("ab");
        VerifyFieldsException ex = assertThrows(VerifyFieldsException.class,
                () -> validator.execute("ab"));
        assertInstanceOf(RuntimeException.class, ex);
    }

    // ─── execute() updates internal state ────────────────────────────────────

    @Test
    @DisplayName("execute() should use the value passed as argument, not constructor value")
    void executeShouldUseArgumentValue() {
        // Constructed with invalid login but executed with a valid one
        LoginValidator validator = new LoginValidator("ab");
        assertDoesNotThrow(() -> validator.execute("validLogin"));
    }

    @Test
    @DisplayName("Should throw when execute() is called with invalid value")
    void shouldThrowWhenExecutedWithInvalidLogin() {
        LoginValidator validator = new LoginValidator("validLogin");
        assertThrows(VerifyFieldsException.class, () -> validator.execute("ab"));
    }
}

