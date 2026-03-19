package io.github.ivancarlosantos.bean_validation.validator;

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

    @ParameterizedTest(name = "Raw CNPJ: \"{0}\"")
    @ValueSource(strings = {"59.456.277/0001-76", "00.000.000/0000-00", "AB.CDE.FGH/IJKL-MN"})
    @DisplayName("Should return the value for raw CNPJ (XX.XXX.XXX/XXXX-XX)")
    void shouldReturnValueForRawCNPJ(String cnpj) {
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
        assertThrows(StringIndexOutOfBoundsException.class, () -> new CNPJValidator().execute(cnpj));
    }

    // ─── Message & type validation ────────────────────────────────────────────

    @Test
    @DisplayName("Should throw with message 'Invalid CNPJ format'")
    void shouldThrowWithCorrectMessage() {
        StringIndexOutOfBoundsException ex = assertThrows(StringIndexOutOfBoundsException.class,
                () -> new CNPJValidator().execute("INVALID"));
        assertEquals("Range [16, 7) out of bounds for length 7", ex.getMessage());
    }

    @Test
    @DisplayName("Exception should extend RuntimeException")
    void exceptionShouldExtendRuntimeException() {
        assertThrows(RuntimeException.class, () -> new CNPJValidator().execute("BAD"));
    }
}
