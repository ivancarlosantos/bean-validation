package io.github.ivancarlosantos.bean_validation.valid;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Valid Interface - Unit Tests")
class ValidTest {

    @Test
    @DisplayName("Should return the value when implementation accepts it")
    void shouldReturnValueWhenValid() {
        Valid valid = value -> value.toUpperCase();
        assertEquals("HELLO", valid.execute("hello"));
    }

    @Test
    @DisplayName("Should execute without throwing for a valid value")
    void shouldExecuteWithValidValue() {
        Valid valid = value -> {
            if (value == null || value.isBlank())
                throw new IllegalArgumentException("blank");
            return value;
        };
        assertDoesNotThrow(() -> valid.execute("valid-value"));
    }

    @Test
    @DisplayName("Should throw when implementation rejects empty value")
    void shouldThrowWhenValueIsEmpty() {
        Valid valid = value -> {
            if (value.isEmpty()) throw new IllegalArgumentException("empty");
            return value;
        };
        assertThrows(IllegalArgumentException.class, () -> valid.execute(""));
    }

    @Test
    @DisplayName("Should throw when implementation rejects null value")
    void shouldThrowWhenValueIsNull() {
        Valid valid = value -> {
            if (value == null) throw new IllegalArgumentException("null");
            return value;
        };
        assertThrows(IllegalArgumentException.class, () -> valid.execute(null));
    }

    @Test
    @DisplayName("Should be implementable as a lambda expression")
    void shouldBeImplementableAsLambda() {
        Valid valid = value -> "processed:" + value;
        assertEquals("processed:test", valid.execute("test"));
    }

    @Test
    @DisplayName("Should propagate any RuntimeException thrown in implementation")
    void shouldPropagateRuntimeException() {
        Valid valid = value -> { throw new RuntimeException("propagated"); };
        RuntimeException ex = assertThrows(RuntimeException.class, () -> valid.execute("any"));
        assertEquals("propagated", ex.getMessage());
    }
}
