package com.bhurb.payments.application.payments.chain;

import java.util.LinkedList;
import java.util.List;

class DefaultPaymentHandlerChain implements PaymentHandlerChain {

    private final LinkedList<PaymentHandler> paymentHandlers;
    private final PaymentHandlerContext paymentHandlerContext;

    public DefaultPaymentHandlerChain(final List<PaymentHandler> paymentHandlers, final PaymentHandlerContext paymentHandlerContext) {
        this.paymentHandlers = new LinkedList<>(paymentHandlers);
        this.paymentHandlerContext = paymentHandlerContext;
    }

    @Override
    public void next() {

        if (this.paymentHandlers.isEmpty()) {
            return;
        }
        var head = this.paymentHandlers.poll();
        head.doNext(this.paymentHandlerContext, this);
    }
}


