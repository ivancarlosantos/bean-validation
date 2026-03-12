package io.github.ivancarlosantos.bean_validation.validator;

import io.github.ivancarlosantos.bean_validation.exception.VerifyFieldsException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("CPFValidator - Unit Tests")
class CPFValidatorTest {

    // ─── Valid inputs (no exception expected) ────────────────────────────────

    @ParameterizedTest(name = "Valid CPF: \"{0}\"")
    @ValueSource(strings = {"123.456.789-09", "987.654.321-00", "000.000.000-00"})
    @DisplayName("Should NOT throw for CPF in formatted pattern (XXX.XXX.XXX-XX)")
    void shouldNotThrowForValidFormattedCPF(String cpf) {
        CPFValidator validator = new CPFValidator(cpf);
        assertDoesNotThrow(() -> validator.execute(cpf));
    }

    // ─── Invalid inputs (exception expected) ─────────────────────────────────

    @ParameterizedTest(name = "Invalid CPF: \"{0}\"")
    @ValueSource(strings = {"BAD", "12345", "badformat", "short"})
    @DisplayName("Should throw VerifyFieldsException for invalid CPF (length != 11 and no regex match)")
    void shouldThrowForInvalidCPF(String cpf) {
        CPFValidator validator = new CPFValidator(cpf);
        assertThrows(VerifyFieldsException.class, () -> validator.execute(cpf));
    }

    // ─── Message validation ───────────────────────────────────────────────────

    @Test
    @DisplayName("Should throw with the correct exception message")
    void shouldThrowWithCorrectMessage() {
        String invalidCpf = "BAD";
        CPFValidator validator = new CPFValidator(invalidCpf);

        VerifyFieldsException ex = assertThrows(
                VerifyFieldsException.class,
                () -> validator.execute(invalidCpf)
        );
        assertEquals("Invalid CPF format", ex.getMessage());
    }

    @Test
    @DisplayName("Exception should be instance of RuntimeException")
    void exceptionShouldBeRuntimeException() {
        CPFValidator validator = new CPFValidator("BAD");
        VerifyFieldsException ex = assertThrows(VerifyFieldsException.class,
                () -> validator.execute("BAD"));
        assertInstanceOf(RuntimeException.class, ex);
    }

    // ─── execute() updates internal state ────────────────────────────────────

    @Test
    @DisplayName("execute() should use the value passed as argument, not constructor value")
    void executeShouldUseArgumentValue() {
        // Constructed with invalid CPF but executed with a valid one
        CPFValidator validator = new CPFValidator("BAD");
        assertDoesNotThrow(() -> validator.execute("123.456.789-09"));
    }
}

