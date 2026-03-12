package io.github.ivancarlosantos.bean_validation.validator;

import io.github.ivancarlosantos.bean_validation.exception.VerifyFieldsException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("CEPValidator - Unit Tests")
class CEPValidatorTest {

    // ─── Valid inputs (no exception, value returned) ─────────────────────────

    @ParameterizedTest(name = "Formatted CEP: \"{0}\"")
    @ValueSource(strings = {"12345-678", "01310-100", "69900-970"})
    @DisplayName("Should return the value for formatted CEP (XXXXX-XXX)")
    void shouldReturnValueForFormattedCEP(String cep) {
        String result = new CEPValidator().execute(cep);
        assertEquals(cep, result);
    }

    @ParameterizedTest(name = "Raw CEP: \"{0}\"")
    @ValueSource(strings = {"12345678", "01310100", "69900970"})
    @DisplayName("Should return the value for raw CEP (XXXXXXXX)")
    void shouldReturnValueForRawCEP(String cep) {
        String result = new CEPValidator().execute(cep);
        assertEquals(cep, result);
    }

    @Test
    @DisplayName("Should not throw for a valid CEP")
    void shouldNotThrowForValidCEP() {
        assertDoesNotThrow(() -> new CEPValidator().execute("12345-678"));
    }

    // ─── Invalid inputs (exception expected) ─────────────────────────────────

    @ParameterizedTest(name = "Invalid CEP: \"{0}\"")
    @ValueSource(strings = {"INVALID", "12345", "1234", "bad"})
    @DisplayName("Should throw VerifyFieldsException for invalid CEP")
    void shouldThrowForInvalidCEP(String cep) {
        assertThrows(VerifyFieldsException.class, () -> new CEPValidator().execute(cep));
    }

    // ─── Message & type validation ────────────────────────────────────────────

    @Test
    @DisplayName("Should throw with message 'Invalid CEP format'")
    void shouldThrowWithCorrectMessage() {
        VerifyFieldsException ex = assertThrows(VerifyFieldsException.class,
                () -> new CEPValidator().execute("INVALID"));
        assertEquals("Invalid CEP format", ex.getMessage());
    }

    @Test
    @DisplayName("Exception should extend RuntimeException")
    void exceptionShouldExtendRuntimeException() {
        assertThrows(RuntimeException.class, () -> new CEPValidator().execute("BAD"));
    }
}
