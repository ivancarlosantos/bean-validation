package io.github.ivancarlosantos.bean_validation.facade;

import io.github.ivancarlosantos.bean_validation.exception.VerifyFieldsException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("ValidationFacade - Unit Tests")
class ValidationFacadeTest {

    // ─────────────────────────────────────────────────────────────────────────
    // Return type: each method returns the validated String
    // ─────────────────────────────────────────────────────────────────────────

    @Test
    @DisplayName("cep() should return the validated value")
    void cepShouldReturnValidatedValue() {
        assertEquals("12345-678", ValidationFacade.cep("12345-678"));
    }

    @Test
    @DisplayName("cpf() should return the validated value")
    void cpfShouldReturnValidatedValue() {
        assertEquals("123.456.789-09", ValidationFacade.cpf("123.456.789-09"));
    }

    @Test
    @DisplayName("email() should return the validated value")
    void emailShouldReturnValidatedValue() {
        assertEquals("test@example.com", ValidationFacade.email("test@example.com"));
    }

    @Test
    @DisplayName("login() should return the validated value")
    void loginShouldReturnValidatedValue() {
        assertEquals("user123", ValidationFacade.login("user123"));
    }

    @Test
    @DisplayName("password() should return the validated value")
    void passwordShouldReturnValidatedValue() {
        assertEquals("Password1@", ValidationFacade.password("Password1@"));
    }

    @Test
    @DisplayName("phone() should return the validated value")
    void phoneShouldReturnValidatedValue() {
        assertEquals("5511999999999", ValidationFacade.phone("5511999999999"));
    }

    // ─────────────────────────────────────────────────────────────────────────
    // Integration: valid inputs → no exception, correct value returned
    // ─────────────────────────────────────────────────────────────────────────

    @Nested
    @DisplayName("Integration – valid inputs should NOT throw and return the value")
    class ValidInputIntegrationTests {

        @ParameterizedTest(name = "Valid CEP: \"{0}\"")
        @ValueSource(strings = {"12345-678", "12345678", "01310-100"})
        @DisplayName("cep() accepts valid CEP formats")
        void cepValidInputs(String cep) {
            assertDoesNotThrow(() -> ValidationFacade.cep(cep));
            assertEquals(cep, ValidationFacade.cep(cep));
        }

        @ParameterizedTest(name = "Valid CPF: \"{0}\"")
        @ValueSource(strings = {"123.456.789-09", "987.654.321-00"})
        @DisplayName("cpf() accepts valid CPF formats")
        void cpfValidInputs(String cpf) {
            assertDoesNotThrow(() -> ValidationFacade.cpf(cpf));
            assertEquals(cpf, ValidationFacade.cpf(cpf));
        }

        @ParameterizedTest(name = "Valid email: \"{0}\"")
        @ValueSource(strings = {"test@example.com", "user.name+tag@domain.org"})
        @DisplayName("email() accepts valid email formats")
        void emailValidInputs(String email) {
            assertDoesNotThrow(() -> ValidationFacade.email(email));
            assertEquals(email, ValidationFacade.email(email));
        }

        @ParameterizedTest(name = "Valid login: \"{0}\"")
        @ValueSource(strings = {"user123", "user_name.ok"})
        @DisplayName("login() accepts valid login formats")
        void loginValidInputs(String login) {
            assertDoesNotThrow(() -> ValidationFacade.login(login));
            assertEquals(login, ValidationFacade.login(login));
        }

        @ParameterizedTest(name = "Valid password: \"{0}\"")
        @ValueSource(strings = {"Password1@", "Str0ng!Pw"})
        @DisplayName("password() accepts valid password formats")
        void passwordValidInputs(String password) {
            assertDoesNotThrow(() -> ValidationFacade.password(password));
            assertEquals(password, ValidationFacade.password(password));
        }

        @ParameterizedTest(name = "Valid phone: \"{0}\"")
        @ValueSource(strings = {"5511999999999", "+55 (11) 99999-9999"})
        @DisplayName("phone() accepts valid phone formats")
        void phoneValidInputs(String phone) {
            assertDoesNotThrow(() -> ValidationFacade.phone(phone));
            assertEquals(phone, ValidationFacade.phone(phone));
        }
    }

    // ─────────────────────────────────────────────────────────────────────────
    // Integration: invalid inputs → correct exception thrown
    // ─────────────────────────────────────────────────────────────────────────

    @Nested
    @DisplayName("Integration – invalid inputs should throw the expected exception")
    class InvalidInputIntegrationTests {

        @Test
        @DisplayName("cep() throws VerifyFieldsException for invalid CEP")
        void cepInvalidThrows() {
            assertThrows(VerifyFieldsException.class, () -> ValidationFacade.cep("INVALID"));
        }

        @Test
        @DisplayName("cpf() throws VerifyFieldsException for invalid CPF")
        void cpfInvalidThrows() {
            assertThrows(VerifyFieldsException.class, () -> ValidationFacade.cpf("BAD"));
        }

        @Test
        @DisplayName("email() throws IllegalArgumentException for invalid email")
        void emailInvalidThrows() {
            assertThrows(IllegalArgumentException.class, () -> ValidationFacade.email("notanemail"));
        }

        @Test
        @DisplayName("login() throws VerifyFieldsException for too-short login")
        void loginInvalidThrows() {
            assertThrows(VerifyFieldsException.class, () -> ValidationFacade.login("ab"));
        }

        @Test
        @DisplayName("password() throws VerifyFieldsException for weak short password")
        void passwordInvalidThrows() {
            assertThrows(VerifyFieldsException.class, () -> ValidationFacade.password("weak"));
        }

        @Test
        @DisplayName("phone() throws VerifyFieldsException for invalid phone")
        void phoneInvalidThrows() {
            assertThrows(VerifyFieldsException.class, () -> ValidationFacade.phone("invalid"));
        }
    }

    // ─────────────────────────────────────────────────────────────────────────
    // Exception message propagation
    // ─────────────────────────────────────────────────────────────────────────

    @Nested
    @DisplayName("Exception messages should be correctly propagated through the facade")
    class ExceptionMessagePropagationTests {

        @Test
        @DisplayName("cep() invalid → 'Invalid CEP format'")
        void cepExceptionMessage() {
            VerifyFieldsException ex = assertThrows(VerifyFieldsException.class,
                    () -> ValidationFacade.cep("INVALID"));
            assertEquals("Invalid CEP format", ex.getMessage());
        }

        @Test
        @DisplayName("cpf() invalid → 'Invalid CPF format'")
        void cpfExceptionMessage() {
            VerifyFieldsException ex = assertThrows(VerifyFieldsException.class,
                    () -> ValidationFacade.cpf("BAD"));
            assertEquals("Invalid CPF format", ex.getMessage());
        }

        @Test
        @DisplayName("email() invalid → 'Invalid email format'")
        void emailExceptionMessage() {
            IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                    () -> ValidationFacade.email("bad"));
            assertEquals("Invalid email format", ex.getMessage());
        }

        @Test
        @DisplayName("login() invalid → 'Invalid Login format'")
        void loginExceptionMessage() {
            VerifyFieldsException ex = assertThrows(VerifyFieldsException.class,
                    () -> ValidationFacade.login("ab"));
            assertEquals("Invalid Login format", ex.getMessage());
        }

        @Test
        @DisplayName("password() invalid → 'Invalid Password format'")
        void passwordExceptionMessage() {
            VerifyFieldsException ex = assertThrows(VerifyFieldsException.class,
                    () -> ValidationFacade.password("weak"));
            assertEquals("Invalid Password format", ex.getMessage());
        }

        @Test
        @DisplayName("phone() invalid → 'Invalid Phone format'")
        void phoneExceptionMessage() {
            VerifyFieldsException ex = assertThrows(VerifyFieldsException.class,
                    () -> ValidationFacade.phone("invalid"));
            assertEquals("Invalid Phone format", ex.getMessage());
        }
    }

    // ─────────────────────────────────────────────────────────────────────────
    // Facade is instantiable (no private constructor)
    // ─────────────────────────────────────────────────────────────────────────

    @Test
    @DisplayName("ValidationFacade should be instantiable (public default constructor)")
    void facadeShouldBeInstantiable() {
        assertDoesNotThrow(() -> assertNotNull(new ValidationFacade()));
    }
}
