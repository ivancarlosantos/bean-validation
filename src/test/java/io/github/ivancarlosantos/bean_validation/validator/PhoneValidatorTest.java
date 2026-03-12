package io.github.ivancarlosantos.bean_validation.validator;

import io.github.ivancarlosantos.bean_validation.exception.VerifyFieldsException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("PhoneValidator - Unit Tests")
class PhoneValidatorTest {

    // ─── Valid inputs (no exception expected) ────────────────────────────────

    @ParameterizedTest(name = "Valid phone: \"{0}\"")
    @ValueSource(strings = {
        "5511999999999",          // 55 + DDD + 9-digit number
        "+5511999999999",         // with + prefix
        "+55 (11) 99999-9999",   // full formatted
        "55(11)99999-9999"        // with area code in parens, no spaces
    })
    @DisplayName("Should NOT throw for valid phone numbers matching the regex")
    void shouldNotThrowForValidPhone(String phone) {
        PhoneValidator validator = new PhoneValidator(phone);
        assertDoesNotThrow(() -> validator.execute(phone));
    }

    // ─── Invalid inputs (exception expected) ─────────────────────────────────
    // Exception thrown only when: !matches regex AND length != 10

    @ParameterizedTest(name = "Invalid phone: \"{0}\"")
    @ValueSource(strings = {
        "invalid",      // length 7, no match
        "abc",          // length 3, no match
        "123456"        // length 6, no match
    })
    @DisplayName("Should throw VerifyFieldsException for invalid phone numbers")
    void shouldThrowForInvalidPhone(String phone) {
        PhoneValidator validator = new PhoneValidator(phone);
        assertThrows(VerifyFieldsException.class, () -> validator.execute(phone));
    }

    // ─── Message validation ───────────────────────────────────────────────────

    @Test
    @DisplayName("Should throw with the correct exception message")
    void shouldThrowWithCorrectMessage() {
        String invalidPhone = "invalid";
        PhoneValidator validator = new PhoneValidator(invalidPhone);

        VerifyFieldsException ex = assertThrows(
                VerifyFieldsException.class,
                () -> validator.execute(invalidPhone)
        );
        assertEquals("Invalid Phone format", ex.getMessage());
    }

    @Test
    @DisplayName("Exception should be instance of RuntimeException")
    void exceptionShouldBeRuntimeException() {
        PhoneValidator validator = new PhoneValidator("bad");
        VerifyFieldsException ex = assertThrows(VerifyFieldsException.class,
                () -> validator.execute("bad"));
        assertInstanceOf(RuntimeException.class, ex);
    }

    // ─── execute() updates internal state ────────────────────────────────────

    @Test
    @DisplayName("execute() should use the value passed as argument, not constructor value")
    void executeShouldUseArgumentValue() {
        // Constructed with invalid phone but executed with a valid one
        PhoneValidator validator = new PhoneValidator("bad");
        assertDoesNotThrow(() -> validator.execute("5511999999999"));
    }

    @Test
    @DisplayName("Should throw when execute() is called with invalid value")
    void shouldThrowWhenExecutedWithInvalidPhone() {
        PhoneValidator validator = new PhoneValidator("5511999999999");
        assertThrows(VerifyFieldsException.class, () -> validator.execute("invalid"));
    }
}

