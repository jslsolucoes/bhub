package com.bhurb.payments.application.payments.chain;

import com.bhurb.payments.junit.AbstractTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

class PaymentHandlerChainProcessorTest extends AbstractTest {


    @Test
    @DisplayName("Chain should call payment handler in any order when all priorities is not defined")
    void chainShouldCallPaymentHandlerInAnyOrderWhenAllPrioritiesIsNotDefined(){

        var called = new ArrayList<PaymentHandler>();

        PaymentHandlerWithPriorityListener priorityPaymentHandlerListener = (paymentHandler, paymentHandlerContext) -> {
            called.add(paymentHandler);
        };
        var paymentHandlerWithNoPriority0 = new PaymentHandlerWithNoPriority0(priorityPaymentHandlerListener);
        var paymentHandlerWithNoPriority1 = new PaymentHandlerWithNoPriority1(priorityPaymentHandlerListener);

        var paymentHandlerChainProcessor = new PaymentHandlerChainProcessor();
        paymentHandlerChainProcessor.register(paymentHandlerWithNoPriority0);
        paymentHandlerChainProcessor.register(paymentHandlerWithNoPriority1);

        var paymentHandlerContext = new PaymentHandlerContext();
        paymentHandlerChainProcessor.next(paymentHandlerContext);

        var expecteds = List.of(
                List.of(paymentHandlerWithNoPriority0, paymentHandlerWithNoPriority1),
                List.of(paymentHandlerWithNoPriority1, paymentHandlerWithNoPriority0)
        );
        var matchesInAnyOrder = expecteds
                .stream()
                .anyMatch(Predicate.isEqual(called));

        assertTrue(called.size() == 2);
        assertTrue(matchesInAnyOrder);

    }


    @Test
    @DisplayName("Chain should respect payment handler priority")
    void chainShouldRespectPaymentHandlerPriority() {

        var called = new ArrayList<PaymentHandler>();

        PaymentHandlerWithPriorityListener priorityPaymentHandlerListener = (paymentHandler, paymentHandlerContext) -> {
            called.add(paymentHandler);
        };
        var paymentHandlerWithPriority0 = new PaymentHandlerWithPriority0(priorityPaymentHandlerListener);
        var paymentHandlerWithPriorityBetween0And1 = new PaymentHandlerWithPriorityBetween0And1(priorityPaymentHandlerListener);
        var paymentHandlerWithPriority1 = new PaymentHandlerWithPriority1(priorityPaymentHandlerListener);

        var paymentHandlerChainProcessor = new PaymentHandlerChainProcessor();
        paymentHandlerChainProcessor.register(paymentHandlerWithPriority1);
        paymentHandlerChainProcessor.register(paymentHandlerWithPriority0);
        paymentHandlerChainProcessor.register(paymentHandlerWithPriorityBetween0And1);

        var paymentHandlerContext = new PaymentHandlerContext();
        paymentHandlerChainProcessor.next(paymentHandlerContext);

        var expected = List.of(paymentHandlerWithPriority0, paymentHandlerWithPriorityBetween0And1, paymentHandlerWithPriority1);
        assertEquals(expected, called);
    }

    @Test
    @DisplayName("Chain should interrupt propagation after first payment handler")
    void chainShouldInterruptPropagationAfterFirstPaymentHandler() {

        var called = new ArrayList<PaymentHandler>();

        PaymentHandlerWithPriorityListener priorityPaymentHandlerListener = (paymentHandler, paymentHandlerContext) -> {
            called.add(paymentHandler);
        };
        var paymentHandlerWithPriority0 = new PaymentHandlerWithPriority0(false, priorityPaymentHandlerListener);
        var paymentHandlerWithPriority1 = new PaymentHandlerWithPriority1(true, priorityPaymentHandlerListener);

        var paymentHandlerChainProcessor = new PaymentHandlerChainProcessor();
        paymentHandlerChainProcessor.register(paymentHandlerWithPriority0);
        paymentHandlerChainProcessor.register(paymentHandlerWithPriority1);

        var paymentHandlerContext = new PaymentHandlerContext();
        paymentHandlerChainProcessor.next(paymentHandlerContext);

        var expected = List.of(paymentHandlerWithPriority0);
        assertEquals(expected, called);
    }

    @Test
    @DisplayName("Payment handler should pass new param context to next payment handler")
    void paymentHandlerShouldPassNewParamContextToNextPaymentHandler() {

        var key = "somekey";
        var value = "somevalue";

        var paymentHandlerWithPriority0 = new PaymentHandlerWithPriority0((paymentHandler, paymentHandlerContext) -> paymentHandlerContext.put(key, value));
        var paymentHandlerWithPriority1 = new PaymentHandlerWithPriority1((paymentHandler, paymentHandlerContext) -> {
            var actual = paymentHandlerContext.get(key);
            assertEquals(value, actual);
        });

        var paymentHandlerChainProcessor = new PaymentHandlerChainProcessor();
        paymentHandlerChainProcessor.register(paymentHandlerWithPriority0);
        paymentHandlerChainProcessor.register(paymentHandlerWithPriority1);

        var paymentHandlerContext = new PaymentHandlerContext();
        paymentHandlerChainProcessor.next(paymentHandlerContext);

    }

    interface PaymentHandlerWithPriorityListener {
        void onNext(final PaymentHandler paymentHandler, final PaymentHandlerContext paymentHandlerContext);
    }


    static abstract class PaymentHandlerWithPriority implements PaymentHandler {
        private final boolean shoulCallNext;
        private final PaymentHandlerWithPriorityListener paymentHandlerWithPriorityListener;

        public PaymentHandlerWithPriority(final boolean shoulCallNext,
                                          final PaymentHandlerWithPriorityListener paymentHandlerWithPriorityListener) {
            this.shoulCallNext = shoulCallNext;
            this.paymentHandlerWithPriorityListener = paymentHandlerWithPriorityListener;
        }

        @Override
        public void doNext(final PaymentHandlerContext paymentHandlerContext,
                           final PaymentHandlerChain paymentHandlerChain) {
            paymentHandlerWithPriorityListener.onNext(this, paymentHandlerContext);
            if (shoulCallNext) {
                paymentHandlerChain.next();
            }
        }
    }

    static class PaymentHandlerWithPriority0 extends PaymentHandlerWithPriority {

        PaymentHandlerWithPriority0(final PaymentHandlerWithPriorityListener paymentHandlerWithPriorityListener) {
            this(true, paymentHandlerWithPriorityListener);
        }

        PaymentHandlerWithPriority0(final boolean shouldCallNext,
                                    final PaymentHandlerWithPriorityListener paymentHandlerWithPriorityListener) {
            super(shouldCallNext, paymentHandlerWithPriorityListener);
        }
    }

    @PaymentHandlerPriority(after = PaymentHandlerWithPriority0.class,
            before = PaymentHandlerWithPriority1.class)
    static class PaymentHandlerWithPriorityBetween0And1 extends PaymentHandlerWithPriority {

        PaymentHandlerWithPriorityBetween0And1(final PaymentHandlerWithPriorityListener paymentHandlerWithPriorityListener) {
            super(true, paymentHandlerWithPriorityListener);
        }
    }

    @PaymentHandlerPriority(after = PaymentHandlerWithPriority0.class)
    static class PaymentHandlerWithPriority1 extends PaymentHandlerWithPriority {
        PaymentHandlerWithPriority1(final PaymentHandlerWithPriorityListener paymentHandlerWithPriorityListener) {
            this(true, paymentHandlerWithPriorityListener);
        }

        PaymentHandlerWithPriority1(final boolean shouldCallNext,
                                    final PaymentHandlerWithPriorityListener paymentHandlerWithPriorityListener) {
            super(shouldCallNext, paymentHandlerWithPriorityListener);
        }
    }


    static class PaymentHandlerWithNoPriority0 extends PaymentHandlerWithPriority {
        PaymentHandlerWithNoPriority0(final PaymentHandlerWithPriorityListener paymentHandlerWithPriorityListener) {
            super(true, paymentHandlerWithPriorityListener);
        }
    }

    static class PaymentHandlerWithNoPriority1 extends PaymentHandlerWithPriority {
        PaymentHandlerWithNoPriority1(final PaymentHandlerWithPriorityListener paymentHandlerWithPriorityListener) {
            super(true, paymentHandlerWithPriorityListener);
        }
    }
}