package io.github.ivancarlosantos.bean_validation.validator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("CPFValidator - Unit Tests")
class CPFValidatorTest {

    // ─── Valid inputs (no exception, value returned) ─────────────────────────

    @ParameterizedTest(name = "Valid CPF: \"{0}\"")
    @ValueSource(strings = {"123.456.789-09", "987.654.321-00", "000.000.000-00"})
    @DisplayName("Should return the value for formatted CPF (XXX.XXX.XXX-XX)")
    void shouldReturnValueForValidCPF(String cpf) {
        String result = new CPFValidator().execute(cpf);
        assertEquals(cpf, result);
    }

    @Test
    @DisplayName("Should not throw for a valid CPF")
    void shouldNotThrowForValidCPF() {
        assertDoesNotThrow(() -> new CPFValidator().execute("123.456.789-09"));
    }

    // ─── Invalid inputs (exception expected) ─────────────────────────────────

    @ParameterizedTest(name = "Invalid CPF: \"{0}\"")
    @ValueSource(strings = {"BAD", "12345", "badformat", "short"})
    @DisplayName("Should throw VerifyFieldsException for invalid CPF (length != 11 and no regex match)")
    void shouldThrowForInvalidCPF(String cpf) {
        assertThrows(StringIndexOutOfBoundsException.class, () -> new CPFValidator().execute(cpf));
    }

    // ─── Message & type validation ────────────────────────────────────────────

    @Test
    @DisplayName("Should throw with message 'Invalid CPF format'")
    void shouldThrowWithCorrectMessage() {
        StringIndexOutOfBoundsException ex = assertThrows(StringIndexOutOfBoundsException.class,
                () -> new CPFValidator().execute("BAD"));
        assertEquals("Range [12, 3) out of bounds for length 3", ex.getMessage());
    }

    @Test
    @DisplayName("Exception should extend RuntimeException")
    void exceptionShouldExtendRuntimeException() {
        assertThrows(RuntimeException.class, () -> new CPFValidator().execute("BAD"));
    }
}
