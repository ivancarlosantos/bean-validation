package io.github.ivancarlosantos.bean_validation.validator;

import io.github.ivancarlosantos.bean_validation.exception.VerifyFieldsException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("LoginValidator - Unit Tests")
class LoginValidatorTest {

    // ─── Valid inputs ─────────────────────────────────────────────────────────

    @ParameterizedTest(name = "Valid login: \"{0}\"")
    @ValueSource(strings = {"user123", "User_Name", "user.name", "user-name", "abc", "login2025", "a1b"})
    @DisplayName("Should return the value for valid login")
    void shouldReturnValueForValidLogin(String login) {
        String result = new LoginValidator().execute(login);
        assertEquals(login, result);
    }

    @Test
    @DisplayName("Should not throw for a valid login")
    void shouldNotThrowForValidLogin() {
        assertDoesNotThrow(() -> new LoginValidator().execute("user123"));
    }

    // ─── Invalid inputs ───────────────────────────────────────────────────────

    @ParameterizedTest(name = "Invalid login: \"{0}\"")
    @ValueSource(strings = {
        "ab",
        "us er",
        "user!",
        "thisLoginNameIsTooLongToBeValid1234"
    })
    @DisplayName("Should throw VerifyFieldsException for invalid login")
    void shouldThrowForInvalidLogin(String login) {
        assertThrows(VerifyFieldsException.class, () -> new LoginValidator().execute(login));
    }

    // ─── Message & type validation ────────────────────────────────────────────

    @Test
    @DisplayName("Should throw with message 'Invalid Login format'")
    void shouldThrowWithCorrectMessage() {
        VerifyFieldsException ex = assertThrows(VerifyFieldsException.class,
                () -> new LoginValidator().execute("ab"));
        assertEquals("Invalid Login format", ex.getMessage());
    }

    @Test
    @DisplayName("Exception should extend RuntimeException")
    void exceptionShouldExtendRuntimeException() {
        assertThrows(RuntimeException.class, () -> new LoginValidator().execute("ab"));
    }
}
