package com.bhurb.payments.application.payments.chain;

public interface PaymentHandler {
    void doNext(final PaymentHandlerContext paymentHandlerContext, final PaymentHandlerChain paymentHandlerChain);

}
