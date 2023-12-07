package com.bhurb.payments.application.payments.chain;


import java.util.HashMap;
import java.util.Map;

public class PaymentHandlerChainProcessor {

    private final Map<Class<? extends PaymentHandler>, PaymentHandler> paymentHandlers;

    public PaymentHandlerChainProcessor() {
        this.paymentHandlers = new HashMap<>();
    }

    public void register(final PaymentHandler paymentHandler) {
        this.paymentHandlers.put(paymentHandler.getClass(), paymentHandler);
    }

    public void next(final PaymentHandlerContext paymentHandlerContext) {
        var paymentHandlerClasses = this.paymentHandlers
                .keySet()
                .toArray(Class<?>[]::new);
        var topologicalSort = new TopologicalSort();
        topologicalSort.register(paymentHandlerClasses);
        var paymentHandlersWithPriority = topologicalSort.all();
        var newPaymentHandlers = paymentHandlersWithPriority
                .stream()
                .map(this.paymentHandlers::get)
                .toList();
        var defaultPaymentHandlerChain = new DefaultPaymentHandlerChain(newPaymentHandlers, paymentHandlerContext);
        defaultPaymentHandlerChain.next();
    }

}
