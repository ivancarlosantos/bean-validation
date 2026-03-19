package io.github.ivancarlosantos.bean_validation.validator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("PhoneValidator - Unit Tests")
class PhoneValidatorTest {

    // ─── Valid inputs ─────────────────────────────────────────────────────────

    @ParameterizedTest(name = "Valid phone: \"{0}\"")
    @ValueSource(strings = {
        "5511999999999",
        "+5511999999999",
        "+55 (11) 99999-9999",
        "55(11)99999-9999"
    })
    @DisplayName("Should return the value for valid phone numbers matching the regex")
    void shouldReturnValueForValidPhone(String phone) {
        String result = new PhoneValidator().execute(phone);
        assertEquals(phone, result);
    }

    @Test
    @DisplayName("Should not throw for a valid phone")
    void shouldNotThrowForValidPhone() {
        assertDoesNotThrow(() -> new PhoneValidator().execute("5511999999999"));
    }

    // ─── Invalid inputs ───────────────────────────────────────────────────────

    @ParameterizedTest(name = "Invalid phone: \"{0}\"")
    @ValueSource(strings = {"invalid", "abc", "123456"})
    @DisplayName("Should throw StringIndexOutOfBoundsException for invalid phone numbers")
    void shouldThrowForInvalidPhone(String phone) {
        assertThrows(StringIndexOutOfBoundsException.class, () -> new PhoneValidator().execute(phone));
    }

    // ─── Message & type validation ────────────────────────────────────────────

    @Test
    @DisplayName("Should throw with message 'Invalid Phone format'")
    void shouldThrowWithCorrectMessage() {
        StringIndexOutOfBoundsException ex = assertThrows(StringIndexOutOfBoundsException.class,
                () -> new PhoneValidator().execute("invalid"));
        assertEquals("Invalid Phone format", ex.getMessage());
    }

    @Test
    @DisplayName("Exception should extend RuntimeException")
    void exceptionShouldExtendRuntimeException() {
        assertThrows(RuntimeException.class, () -> new PhoneValidator().execute("bad"));
    }
}
