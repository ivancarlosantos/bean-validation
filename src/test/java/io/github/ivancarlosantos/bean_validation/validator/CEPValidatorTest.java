package io.github.ivancarlosantos.bean_validation.validator;

import io.github.ivancarlosantos.bean_validation.exception.VerifyFieldsException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("CEPValidator - Unit Tests")
class CEPValidatorTest {

    // ─── Valid inputs (no exception expected) ────────────────────────────────

    @ParameterizedTest(name = "Valid CEP with dash: \"{0}\"")
    @ValueSource(strings = {"12345-678", "01310-100", "69900-970"})
    @DisplayName("Should NOT throw for CEP in formatted pattern (XXXXX-XXX)")
    void shouldNotThrowForValidFormattedCEP(String cep) {
        CEPValidator validator = new CEPValidator(cep);
        assertDoesNotThrow(() -> validator.execute(cep));
    }

    @ParameterizedTest(name = "Valid CEP without dash: \"{0}\"")
    @ValueSource(strings = {"12345678", "01310100", "69900970"})
    @DisplayName("Should NOT throw for CEP in raw digits pattern (XXXXXXXX)")
    void shouldNotThrowForValidRawCEP(String cep) {
        CEPValidator validator = new CEPValidator(cep);
        assertDoesNotThrow(() -> validator.execute(cep));
    }

    // ─── Invalid inputs (exception expected) ─────────────────────────────────

    @ParameterizedTest(name = "Invalid CEP: \"{0}\"")
    @ValueSource(strings = {"INVALID", "12345", "1234", "bad"})
    @DisplayName("Should throw VerifyFieldsException for invalid CEP")
    void shouldThrowForInvalidCEP(String cep) {
        CEPValidator validator = new CEPValidator(cep);
        assertThrows(VerifyFieldsException.class, () -> validator.execute(cep));
    }

    // ─── Message validation ───────────────────────────────────────────────────

    @Test
    @DisplayName("Should throw with the correct exception message")
    void shouldThrowWithCorrectMessage() {
        String invalidCep = "INVALID";
        CEPValidator validator = new CEPValidator(invalidCep);

        VerifyFieldsException ex = assertThrows(
                VerifyFieldsException.class,
                () -> validator.execute(invalidCep)
        );
        assertEquals("Invalid CEP format", ex.getMessage());
    }

    @Test
    @DisplayName("Exception should be instance of VerifyFieldsException and RuntimeException")
    void exceptionShouldBeRuntimeException() {
        CEPValidator validator = new CEPValidator("BAD");
        VerifyFieldsException ex = assertThrows(VerifyFieldsException.class,
                () -> validator.execute("BAD"));
        assertInstanceOf(RuntimeException.class, ex);
    }

    // ─── execute() updates internal state ────────────────────────────────────

    @Test
    @DisplayName("execute() should use the value passed as argument, not constructor value")
    void executeShouldUseArgumentValue() {
        // Constructed with invalid CEP but executed with a valid one
        CEPValidator validator = new CEPValidator("BAD");
        assertDoesNotThrow(() -> validator.execute("12345-678"));
    }
}

