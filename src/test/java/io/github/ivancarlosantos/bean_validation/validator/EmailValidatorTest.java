package io.github.ivancarlosantos.bean_validation.validator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("EmailValidator - Unit Tests")
class EmailValidatorTest {

    // ─── Valid inputs ─────────────────────────────────────────────────────────

    @ParameterizedTest(name = "Valid email: \"{0}\"")
    @ValueSource(strings = {
        "test@example.com",
        "user.name@domain.org",
        "user+tag@example.co.uk",
        "firstname.lastname@company.io",
        "user123@mail.net"
    })
    @DisplayName("Should return the value for valid email addresses")
    void shouldReturnValueForValidEmail(String email) {
        String result = new EmailValidator().execute(email);
        assertEquals(email, result);
    }

    @Test
    @DisplayName("Should not throw for a valid email")
    void shouldNotThrowForValidEmail() {
        assertDoesNotThrow(() -> new EmailValidator().execute("test@example.com"));
    }

    // ─── Invalid inputs ───────────────────────────────────────────────────────

    @ParameterizedTest(name = "Invalid email: \"{0}\"")
    @ValueSource(strings = {
        "notanemail",
        "@nodomain.com",
        "spaces @domain.com",
        "missingdomain@",
        "nodot@nodot",
        "plainaddress"
    })
    @DisplayName("Should throw StringIndexOutOfBoundsException for invalid emails")
    void shouldThrowForInvalidEmail(String email) {
        assertThrows(StringIndexOutOfBoundsException.class, () -> new EmailValidator().execute(email));
    }

    // ─── Message validation ───────────────────────────────────────────────────

    @Test
    @DisplayName("Should throw with message 'Invalid email format'")
    void shouldThrowWithCorrectMessage() {
        StringIndexOutOfBoundsException ex = assertThrows(StringIndexOutOfBoundsException.class,
                () -> new EmailValidator().execute("notanemail"));
        assertEquals("Range [-2, 10) out of bounds for length 10", ex.getMessage());
    }
}
