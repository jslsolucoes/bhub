package com.bhurb.payments.application.payments.chain;

import com.bhurb.payments.junit.AbstractTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

class TopologicalSortTest extends AbstractTest {

    @Test
    @DisplayName("Should detect circular payment handler cycle")
    void shouldDetectCircularPaymentHandlerCycle() {
        var topologicalSort = new TopologicalSort();
        topologicalSort.register(PaymentHandlerA.class, PaymentHandlerB.class);
        var illegalArgumentException = assertThrows(IllegalStateException.class, () -> {
            topologicalSort.all();
        });
        var message = illegalArgumentException.getMessage();
        assertTrue(message.startsWith("There is a cycle on the payment handler sequence:"));
    }

    @Test
    @DisplayName("Should order correctly payment handlers")
    void shouldOrderCorrectlyPaymentHandlers() {
        var topologicalSort = new TopologicalSort();
        topologicalSort.register(PaymentHandlerB.class, PaymentHandlerC.class);
        var all = topologicalSort.all();
        var expected = List.of(PaymentHandlerA.class, PaymentHandlerB.class, PaymentHandlerC.class);
        assertEquals(expected, all);
    }

    @PaymentHandlerPriority(after = PaymentHandlerB.class)
    static class PaymentHandlerC implements PaymentHandler {
        @Override
        public void doNext(final PaymentHandlerContext paymentHandlerContext, final PaymentHandlerChain paymentHandlerChain) {
            paymentHandlerChain.next();
        }
    }

    @PaymentHandlerPriority(after = PaymentHandlerB.class)
    static class PaymentHandlerA implements PaymentHandler {
        @Override
        public void doNext(final PaymentHandlerContext paymentHandlerContext, final PaymentHandlerChain paymentHandlerChain) {
            paymentHandlerChain.next();
        }

    }

    @PaymentHandlerPriority(after = PaymentHandlerA.class)
    static class PaymentHandlerB implements PaymentHandler {
        @Override
        public void doNext(final PaymentHandlerContext paymentHandlerContext, final PaymentHandlerChain paymentHandlerChain) {
            paymentHandlerChain.next();
        }

    }

}