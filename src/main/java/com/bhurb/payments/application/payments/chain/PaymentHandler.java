package com.bhurb.payments.application.payments.chain;

/*
   Interface to be implemented by all the handlers in the chain
 */
public interface PaymentHandler {
    void doNext(final PaymentHandlerContext paymentHandlerContext, final PaymentHandlerChain paymentHandlerChain);

}
