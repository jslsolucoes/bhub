package com.bhurb.payments.junit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.function.Executable;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles(value = "test")
public abstract class AbstractTest {

    public void assertEquals(final String expected, final String actual) {
        Assertions.assertEquals(expected, actual);
    }

    public void assertEquals(final Object expected, final Object actual) {
        Assertions.assertEquals(expected, actual);
    }

    public void assertTrue(final boolean condition) {
        Assertions.assertTrue(condition);
    }

    public void assertSuccess() {
        assertTrue(true);
    }

    public Throwable assertThrows(final Class<? extends Throwable> expectedType, Executable executable) {
        return Assertions.assertThrows(expectedType, executable);
    }
}