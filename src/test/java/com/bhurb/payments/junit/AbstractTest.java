package com.bhurb.payments.junit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mockito;
import org.mockito.stubbing.OngoingStubbing;
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

    public void assertFalse(final boolean condition) {
        Assertions.assertFalse(condition);
    }

    public Throwable assertThrows(final Class<? extends Throwable> expectedType, Executable executable) {
        return Assertions.assertThrows(expectedType, executable);
    }
    public <T> OngoingStubbing<T> when(final T methodCall) {
        return Mockito.when(methodCall);
    }
    public <T> T verify(final T mock) {
        return Mockito.verify(mock);
    }
    public <T> T any() {
        return Mockito.any();
    }

    public String eq(final String value) {
        return Mockito.eq(value);
    }
}