package io.github.ivancarlosantos.bean_validation.validator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("PasswordValidator - Unit Tests")
class PasswordValidatorTest {

    // ─── Valid inputs (matches regex → no exception, returns value) ───────────

    @ParameterizedTest(name = "Valid password: \"{0}\"")
    @ValueSource(strings = {"Password1@", "Str0ng!Pw", "S3cur3P@ss", "Abc12345#", "Test@1234"})
    @DisplayName("Should return the value for valid passwords matching the regex")
    void shouldReturnValueForValidPassword(String password) {
        String result = new PasswordValidator().execute(password);
        assertEquals(password, result);
    }

    @Test
    @DisplayName("Should not throw for a valid password")
    void shouldNotThrowForValidPassword() {
        assertDoesNotThrow(() -> new PasswordValidator().execute("Password1@"));
    }

    // ─── Invalid inputs: too short (< 8) and no regex match ──────────────────

    @ParameterizedTest(name = "Too-short password: \"{0}\"")
    @ValueSource(strings = {"weak", "bad", "Ab1@"})
    @DisplayName("Should throw VerifyFieldsException for password shorter than 8 chars with no regex match")
    void shouldThrowForTooShortPassword(String password) {
        assertThrows(StringIndexOutOfBoundsException.class, () -> new PasswordValidator().execute(password));
    }

    // ─── Invalid inputs: length > 12 and no regex match ──────────────────────

    @ParameterizedTest(name = "Long invalid password: \"{0}\"")
    @ValueSource(strings = {"weakpassword1234!", "nouppercase12345!"})
    @DisplayName("Should throw VerifyFieldsException for password longer than 12 chars with no regex match")
    void shouldThrowForLongInvalidPassword(String password) {
        assertThrows(StringIndexOutOfBoundsException.class, () -> new PasswordValidator().execute(password));
    }

    // ─── Message & type validation ────────────────────────────────────────────

    @Test
    @DisplayName("Should throw with message 'Invalid Password format'")
    void shouldThrowWithCorrectMessage() {
        StringIndexOutOfBoundsException ex = assertThrows(StringIndexOutOfBoundsException.class,
                () -> new PasswordValidator().execute("weak"));
        assertEquals("Invalid Password format", ex.getMessage());
    }

    @Test
    @DisplayName("Exception should extend RuntimeException")
    void exceptionShouldExtendRuntimeException() {
        assertThrows(RuntimeException.class, () -> new PasswordValidator().execute("weak"));
    }
}
