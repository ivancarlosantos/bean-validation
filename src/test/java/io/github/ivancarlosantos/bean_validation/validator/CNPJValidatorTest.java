package io.github.ivancarlosantos.bean_validation.validator;

import io.github.ivancarlosantos.bean_validation.exception.VerifyFieldsException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("CNPJValidator - Unit Tests")
public class CNPJValidatorTest {

    // ─── Valid inputs (no exception, value returned) ─────────────────────────

    @ParameterizedTest(name = "Formatted CNPJ: \"{0}\"")
    @ValueSource(strings = {"59.456.277/0001-76", "12.345.678/9012-34"})
    @DisplayName("Should return the value for formatted CNPJ (XX.XXX.XXX/XXXX-XX)")
    void shouldReturnValueForFormattedCNPJ(String cnpj) {
        String result = new CNPJValidator().execute(cnpj);
        assertEquals(cnpj, result);
    }

    @ParameterizedTest(name = "Additional valid CNPJ: \"{0}\"")
    @ValueSource(strings = {"00.000.000/0000-00", "12.345.678/9012-34"})
    @DisplayName("Should return the value for additional valid CNPJ cases")
    void shouldReturnValueForAdditionalValidCNPJ(String cnpj) {
        String result = new CNPJValidator().execute(cnpj);
        assertEquals(cnpj, result);
    }

    @Test
    @DisplayName("Should not throw for a valid CNPJ")
    void shouldNotThrowForValidCNPJ() {
        assertDoesNotThrow(() -> new CNPJValidator().execute("59.456.277/0001-76"));
    }

    // ─── Invalid inputs (exception expected) ─────────────────────────────────

    @ParameterizedTest(name = "Invalid CNPJ: \"{0}\"")
    @ValueSource(strings = {"INVALID", "59.456.277/00001-760", "1234", "bad"})
    @DisplayName("Should throw VerifyFieldsException for invalid CNPJ")
    void shouldThrowForInvalidCNPJ(String cnpj) {
        assertThrows(VerifyFieldsException.class, () -> new CNPJValidator().execute(cnpj));
    }

    // ─── Message & type validation ────────────────────────────────────────────

    @Test
    @DisplayName("Should throw with message 'Invalid CNPJ format'")
    void shouldThrowWithCorrectMessage() {
        VerifyFieldsException ex = assertThrows(VerifyFieldsException.class,
                () -> new CNPJValidator().execute("INVALID"));
        assertEquals("Invalid CNPJ format", ex.getMessage());
    }

    @Test
    @DisplayName("Exception should extend RuntimeException")
    void exceptionShouldExtendRuntimeException() {
        assertThrows(VerifyFieldsException.class, () -> new CNPJValidator().execute("BAD"));
    }
}
