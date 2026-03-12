package io.github.ivancarlosantos.bean_validation.exception;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("VerifyFieldsException - Unit Tests")
class VerifyFieldsExceptionTest {

    @Test
    @DisplayName("Should create exception with the provided message")
    void shouldCreateExceptionWithMessage() {
        String message = "Field validation failed";
        VerifyFieldsException exception = new VerifyFieldsException(message);
        assertEquals(message, exception.getMessage());
    }

    @Test
    @DisplayName("Should extend RuntimeException")
    void shouldExtendRuntimeException() {
        VerifyFieldsException exception = new VerifyFieldsException("error");
        assertInstanceOf(RuntimeException.class, exception);
    }

    @Test
    @DisplayName("Should be throwable and catchable")
    void shouldThrowAndBeCaught() {
        String expectedMessage = "Invalid field value";
        VerifyFieldsException thrown = assertThrows(
                VerifyFieldsException.class,
                () -> { throw new VerifyFieldsException(expectedMessage); }
        );
        assertEquals(expectedMessage, thrown.getMessage());
    }

    @Test
    @DisplayName("Should preserve message after being thrown")
    void shouldPreserveMessageAfterThrown() {
        String message = "Test preserve message";
        try {
            throw new VerifyFieldsException(message);
        } catch (VerifyFieldsException e) {
            assertEquals(message, e.getMessage());
            assertInstanceOf(RuntimeException.class, e);
        }
    }

    @Test
    @DisplayName("Should accept empty string as message")
    void shouldAcceptEmptyMessage() {
        VerifyFieldsException exception = new VerifyFieldsException("");
        assertEquals("", exception.getMessage());
    }

    @Test
    @DisplayName("Should accept null message")
    void shouldAcceptNullMessage() {
        VerifyFieldsException exception = new VerifyFieldsException(null);
        assertNull(exception.getMessage());
    }
}

