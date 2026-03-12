package io.github.ivancarlosantos.bean_validation.valid;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Valid Interface - Unit Tests")
class ValidTest {

    @Test
    @DisplayName("Should execute without throwing for a valid value")
    void shouldExecuteWithValidValue() {
        Valid valid = value -> {
            if (value == null || value.isBlank()) {
                throw new IllegalArgumentException("Value must not be blank");
            }
        };
        assertDoesNotThrow(() -> valid.execute("valid-value"));
    }

    @Test
    @DisplayName("Should throw when implementation rejects empty value")
    void shouldThrowWhenValueIsEmpty() {
        Valid valid = value -> {
            if (value == null || value.isEmpty()) {
                throw new IllegalArgumentException("Value cannot be empty");
            }
        };
        assertThrows(IllegalArgumentException.class, () -> valid.execute(""));
    }

    @Test
    @DisplayName("Should throw when implementation rejects null value")
    void shouldThrowWhenValueIsNull() {
        Valid valid = value -> {
            if (value == null) {
                throw new IllegalArgumentException("Value cannot be null");
            }
        };
        assertThrows(IllegalArgumentException.class, () -> valid.execute(null));
    }

    @Test
    @DisplayName("Should be implementable as a lambda expression")
    void shouldBeImplementableAsLambda() {
        final String[] captured = {null};
        Valid valid = value -> captured[0] = value;

        valid.execute("captured-value");

        assertEquals("captured-value", captured[0]);
    }

    @Test
    @DisplayName("Should allow multiple executions with different values")
    void shouldAllowMultipleExecutions() {
        final int[] counter = {0};
        Valid valid = value -> counter[0]++;

        valid.execute("first");
        valid.execute("second");
        valid.execute("third");

        assertEquals(3, counter[0]);
    }

    @Test
    @DisplayName("Should propagate any RuntimeException thrown in implementation")
    void shouldPropagateRuntimeException() {
        Valid valid = value -> {
            throw new RuntimeException("propagated");
        };
        RuntimeException ex = assertThrows(RuntimeException.class, () -> valid.execute("any"));
        assertEquals("propagated", ex.getMessage());
    }
}

