package com.bhurb.payments.domain.model.valueobject;

import com.bhurb.payments.junit.AbstractTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class EmailTest extends AbstractTest {

    @Test
    @DisplayName("Should create a valid email")
    void shouldCreateAValidEmail() {
        var emailAddres = "aa@aa.com";
        var email = new Email(emailAddres);
        assertEquals(emailAddres, email.address());
    }

    @ParameterizedTest
    @DisplayName("Should throw exception when email is invalid")
    @ValueSource(strings = {"aa.com", "aa@aa", "aa@aa."})
    void shouldThrowExceptionWhenEmailIsInvalid(final String emailAddress) {
        var illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> new Email(emailAddress));
        assertEquals("Invalid email address", illegalArgumentException.getMessage());
    }
}