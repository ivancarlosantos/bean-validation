package io.github.ivancarlosantos.bean_validation.facade;

import io.github.ivancarlosantos.bean_validation.exception.VerifyFieldsException;
import io.github.ivancarlosantos.bean_validation.validator.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("ValidationFacade - Unit Tests")
class ValidationFacadeTest {

    // ─────────────────────────────────────────────────────────────────────────
    // Factory method return type & non-null
    // ─────────────────────────────────────────────────────────────────────────

    @Test
    @DisplayName("cep() should return a non-null CEPValidator instance")
    void cepShouldReturnNonNullCEPValidator() {
        CEPValidator validator = ValidationFacade.cep("12345-678");
        assertNotNull(validator);
        assertInstanceOf(CEPValidator.class, validator);
    }

    @Test
    @DisplayName("cpf() should return a non-null CPFValidator instance")
    void cpfShouldReturnNonNullCPFValidator() {
        CPFValidator validator = ValidationFacade.cpf("123.456.789-09");
        assertNotNull(validator);
        assertInstanceOf(CPFValidator.class, validator);
    }

    @Test
    @DisplayName("email() should return a non-null EmailValidator instance")
    void emailShouldReturnNonNullEmailValidator() {
        EmailValidator validator = ValidationFacade.email("test@example.com");
        assertNotNull(validator);
        assertInstanceOf(EmailValidator.class, validator);
    }

    @Test
    @DisplayName("login() should return a non-null LoginValidator instance")
    void loginShouldReturnNonNullLoginValidator() {
        LoginValidator validator = ValidationFacade.login("user123");
        assertNotNull(validator);
        assertInstanceOf(LoginValidator.class, validator);
    }

    @Test
    @DisplayName("password() should return a non-null PasswordValidator instance")
    void passwordShouldReturnNonNullPasswordValidator() {
        PasswordValidator validator = ValidationFacade.password("Password1@");
        assertNotNull(validator);
        assertInstanceOf(PasswordValidator.class, validator);
    }

    @Test
    @DisplayName("phone() should return a non-null PhoneValidator instance")
    void phoneShouldReturnNonNullPhoneValidator() {
        PhoneValidator validator = ValidationFacade.phone("5511999999999");
        assertNotNull(validator);
        assertInstanceOf(PhoneValidator.class, validator);
    }

    // ─────────────────────────────────────────────────────────────────────────
    // Each factory call produces a distinct instance
    // ─────────────────────────────────────────────────────────────────────────

    @Test
    @DisplayName("cep() should return a new instance on each call")
    void cepShouldReturnNewInstanceOnEachCall() {
        CEPValidator v1 = ValidationFacade.cep("12345-678");
        CEPValidator v2 = ValidationFacade.cep("12345-678");
        assertNotSame(v1, v2);
    }

    @Test
    @DisplayName("cpf() should return a new instance on each call")
    void cpfShouldReturnNewInstanceOnEachCall() {
        CPFValidator v1 = ValidationFacade.cpf("123.456.789-09");
        CPFValidator v2 = ValidationFacade.cpf("123.456.789-09");
        assertNotSame(v1, v2);
    }

    @Test
    @DisplayName("email() should return a new instance on each call")
    void emailShouldReturnNewInstanceOnEachCall() {
        EmailValidator v1 = ValidationFacade.email("test@example.com");
        EmailValidator v2 = ValidationFacade.email("test@example.com");
        assertNotSame(v1, v2);
    }

    @Test
    @DisplayName("login() should return a new instance on each call")
    void loginShouldReturnNewInstanceOnEachCall() {
        LoginValidator v1 = ValidationFacade.login("user123");
        LoginValidator v2 = ValidationFacade.login("user123");
        assertNotSame(v1, v2);
    }

    @Test
    @DisplayName("password() should return a new instance on each call")
    void passwordShouldReturnNewInstanceOnEachCall() {
        PasswordValidator v1 = ValidationFacade.password("Password1@");
        PasswordValidator v2 = ValidationFacade.password("Password1@");
        assertNotSame(v1, v2);
    }

    @Test
    @DisplayName("phone() should return a new instance on each call")
    void phoneShouldReturnNewInstanceOnEachCall() {
        PhoneValidator v1 = ValidationFacade.phone("5511999999999");
        PhoneValidator v2 = ValidationFacade.phone("5511999999999");
        assertNotSame(v1, v2);
    }

    // ─────────────────────────────────────────────────────────────────────────
    // Integration: valid input → no exception
    // ─────────────────────────────────────────────────────────────────────────

    @Nested
    @DisplayName("Integration – valid inputs should NOT throw any exception")
    class ValidInputIntegrationTests {

        @Test
        @DisplayName("cep() with valid CEP executes without exception")
        void cepValidInputNoException() {
            assertDoesNotThrow(() -> ValidationFacade.cep("12345-678").execute("12345-678"));
        }

        @Test
        @DisplayName("cep() with raw valid CEP executes without exception")
        void cepRawValidInputNoException() {
            assertDoesNotThrow(() -> ValidationFacade.cep("12345678").execute("12345678"));
        }

        @Test
        @DisplayName("cpf() with valid CPF executes without exception")
        void cpfValidInputNoException() {
            assertDoesNotThrow(() -> ValidationFacade.cpf("123.456.789-09").execute("123.456.789-09"));
        }

        @Test
        @DisplayName("email() with valid email executes without exception")
        void emailValidInputNoException() {
            assertDoesNotThrow(() -> ValidationFacade.email("test@example.com").execute("test@example.com"));
        }

        @Test
        @DisplayName("email() with complex valid email executes without exception")
        void emailComplexValidInputNoException() {
            assertDoesNotThrow(() -> ValidationFacade.email("user.name+tag@sub.domain.org")
                    .execute("user.name+tag@sub.domain.org"));
        }

        @Test
        @DisplayName("login() with valid login executes without exception")
        void loginValidInputNoException() {
            assertDoesNotThrow(() -> ValidationFacade.login("user123").execute("user123"));
        }

        @Test
        @DisplayName("login() with underscore and dot executes without exception")
        void loginWithSpecialCharsNoException() {
            assertDoesNotThrow(() -> ValidationFacade.login("user_name.ok").execute("user_name.ok"));
        }

        @Test
        @DisplayName("password() with valid password executes without exception")
        void passwordValidInputNoException() {
            assertDoesNotThrow(() -> ValidationFacade.password("Password1@").execute("Password1@"));
        }

        @Test
        @DisplayName("password() with another strong password executes without exception")
        void passwordAnotherValidInputNoException() {
            assertDoesNotThrow(() -> ValidationFacade.password("Str0ng!Pw").execute("Str0ng!Pw"));
        }

        @Test
        @DisplayName("phone() with valid phone executes without exception")
        void phoneValidInputNoException() {
            assertDoesNotThrow(() -> ValidationFacade.phone("5511999999999").execute("5511999999999"));
        }

        @Test
        @DisplayName("phone() with full formatted phone executes without exception")
        void phoneFormattedValidInputNoException() {
            assertDoesNotThrow(() -> ValidationFacade
                    .phone("+55 (11) 99999-9999")
                    .execute("+55 (11) 99999-9999"));
        }
    }

    // ─────────────────────────────────────────────────────────────────────────
    // Integration: invalid input → exception
    // ─────────────────────────────────────────────────────────────────────────

    @Nested
    @DisplayName("Integration – invalid inputs should throw the expected exception")
    class InvalidInputIntegrationTests {

        @Test
        @DisplayName("cep() with invalid CEP throws VerifyFieldsException")
        void cepInvalidInputThrowsException() {
            assertThrows(VerifyFieldsException.class,
                    () -> ValidationFacade.cep("INVALID").execute("INVALID"));
        }

        @Test
        @DisplayName("cpf() with invalid CPF throws VerifyFieldsException")
        void cpfInvalidInputThrowsException() {
            assertThrows(VerifyFieldsException.class,
                    () -> ValidationFacade.cpf("BAD").execute("BAD"));
        }

        @Test
        @DisplayName("email() with invalid email throws IllegalArgumentException")
        void emailInvalidInputThrowsException() {
            assertThrows(IllegalArgumentException.class,
                    () -> ValidationFacade.email("notanemail").execute("notanemail"));
        }

        @Test
        @DisplayName("login() with too-short login throws VerifyFieldsException")
        void loginTooShortThrowsException() {
            assertThrows(VerifyFieldsException.class,
                    () -> ValidationFacade.login("ab").execute("ab"));
        }

        @Test
        @DisplayName("login() with special chars throws VerifyFieldsException")
        void loginSpecialCharThrowsException() {
            assertThrows(VerifyFieldsException.class,
                    () -> ValidationFacade.login("user!").execute("user!"));
        }

        @Test
        @DisplayName("password() with too-short weak password throws VerifyFieldsException")
        void passwordTooShortThrowsException() {
            assertThrows(VerifyFieldsException.class,
                    () -> ValidationFacade.password("weak").execute("weak"));
        }

        @Test
        @DisplayName("password() with long invalid password throws VerifyFieldsException")
        void passwordLongInvalidThrowsException() {
            assertThrows(VerifyFieldsException.class,
                    () -> ValidationFacade.password("weakpassword1234!").execute("weakpassword1234!"));
        }

        @Test
        @DisplayName("phone() with invalid phone throws VerifyFieldsException")
        void phoneInvalidInputThrowsException() {
            assertThrows(VerifyFieldsException.class,
                    () -> ValidationFacade.phone("invalid").execute("invalid"));
        }
    }

    // ─────────────────────────────────────────────────────────────────────────
    // Exception message propagation through the facade
    // ─────────────────────────────────────────────────────────────────────────

    @Nested
    @DisplayName("Exception messages should be correctly propagated through the facade")
    class ExceptionMessagePropagationTests {

        @Test
        @DisplayName("cep() invalid → message 'Invalid CEP format'")
        void cepExceptionMessage() {
            VerifyFieldsException ex = assertThrows(VerifyFieldsException.class,
                    () -> ValidationFacade.cep("INVALID").execute("INVALID"));
            assertEquals("Invalid CEP format", ex.getMessage());
        }

        @Test
        @DisplayName("cpf() invalid → message 'Invalid CPF format'")
        void cpfExceptionMessage() {
            VerifyFieldsException ex = assertThrows(VerifyFieldsException.class,
                    () -> ValidationFacade.cpf("BAD").execute("BAD"));
            assertEquals("Invalid CPF format", ex.getMessage());
        }

        @Test
        @DisplayName("email() invalid → message 'Invalid email format'")
        void emailExceptionMessage() {
            IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                    () -> ValidationFacade.email("bad").execute("bad"));
            assertEquals("Invalid email format", ex.getMessage());
        }

        @Test
        @DisplayName("login() invalid → message 'Invalid Login format'")
        void loginExceptionMessage() {
            VerifyFieldsException ex = assertThrows(VerifyFieldsException.class,
                    () -> ValidationFacade.login("ab").execute("ab"));
            assertEquals("Invalid Login format", ex.getMessage());
        }

        @Test
        @DisplayName("password() invalid → message 'Invalid Password format'")
        void passwordExceptionMessage() {
            VerifyFieldsException ex = assertThrows(VerifyFieldsException.class,
                    () -> ValidationFacade.password("weak").execute("weak"));
            assertEquals("Invalid Password format", ex.getMessage());
        }

        @Test
        @DisplayName("phone() invalid → message 'Invalid Phone format'")
        void phoneExceptionMessage() {
            VerifyFieldsException ex = assertThrows(VerifyFieldsException.class,
                    () -> ValidationFacade.phone("invalid").execute("invalid"));
            assertEquals("Invalid Phone format", ex.getMessage());
        }
    }

    // ─────────────────────────────────────────────────────────────────────────
    // Facade is instantiable (no private constructor)
    // ─────────────────────────────────────────────────────────────────────────

    @Test
    @DisplayName("ValidationFacade should be instantiable (public default constructor)")
    void facadeShouldBeInstantiable() {
        assertDoesNotThrow(() -> {
            ValidationFacade facade = new ValidationFacade();
            assertNotNull(facade);
        });
    }
}

